package ma.enset.hospitalapp.security.service.serviceImpl;

import lombok.AllArgsConstructor;
import ma.enset.hospitalapp.security.entities.AppRole;
import ma.enset.hospitalapp.security.entities.AppUser;
import ma.enset.hospitalapp.security.repositories.AppRoleRepository;
import ma.enset.hospitalapp.security.repositories.AppUserRepository;
import ma.enset.hospitalapp.security.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(AppUser appUser, String CnfPass) {
        AppUser u = appUserRepository.findByUsername(appUser.getUsername());
        if(u!=null) throw new RuntimeException(String.format("c'est utilisateur %s déja exist"));
        if(appUser.getPassword().equals(CnfPass)) throw new RuntimeException("mote de passe ne correspond pas");
        u = AppUser.builder()
                .Id(UUID.randomUUID().toString())
                .username(appUser.getUsername())
                .password(passwordEncoder.encode(appUser.getPassword()))
                .email(appUser.getEmail())
                .build();
        AppUser saved = appUserRepository.save(u);
        return saved;
    }

    @Override
    public AppRole addROle(AppRole appRole) {
        AppRole r =appRoleRepository.findById(appRole.getId()).orElse(null);
        if (r!=null) throw new RuntimeException(String.format("c'est role %s déja exist"));
        r = AppRole.builder()
                .id(UUID.randomUUID().toString())
                .role(appRole.getRole())
                .build();
        AppRole saved = appRoleRepository.save(r);
        return saved;
    }

    @Override
    public void addRoleToUser(AppUser user, AppRole appRole) {
        AppUser id = appUserRepository.findByUsername(user.getUsername());
        AppRole role = appRoleRepository.findById(appRole.getId()).get();
        id.getAppRoles().add(role);
    }

    @Override
    public void removeRoleToUser(String Username, String Role) {
        AppUser id = appUserRepository.findByUsername(Username);
        AppRole role = appRoleRepository.findById(Role).get();
        id.getAppRoles().add(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
