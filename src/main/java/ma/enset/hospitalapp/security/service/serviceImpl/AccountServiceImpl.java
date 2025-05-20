package ma.enset.hospitalapp.security.service.serviceImpl;

import lombok.AllArgsConstructor;
import ma.enset.hospitalapp.security.entities.AppRole;
import ma.enset.hospitalapp.security.entities.AppUser;
import ma.enset.hospitalapp.security.repositories.AppRoleRepository;
import ma.enset.hospitalapp.security.repositories.AppUserRepository;
import ma.enset.hospitalapp.security.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(String username, String password, String email, String CnfPass) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null)throw new RuntimeException(String.format("c'est utilisateur %s déja exist"));
        if(!password.equals(CnfPass)) throw new RuntimeException("mote de passe ne correspond pas");
        user = AppUser.builder()
                .Id(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser saved = appUserRepository.save(user);
        return saved;
    }

    @Override
    public AppRole addRole(String role) {
        AppRole r =appRoleRepository.findById(role).orElse(null);
        if (r!=null) throw new RuntimeException(String.format("c'est role %s déja exist"));
        r = AppRole.builder()
                .role(role)
                .build();
        AppRole saved = appRoleRepository.save(r);
        return saved;
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String Username, String role) {
        AppUser appUser = appUserRepository.findByUsername(Username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
