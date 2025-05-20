package ma.enset.hospitalapp.security.service;

import ma.enset.hospitalapp.security.entities.AppRole;
import ma.enset.hospitalapp.security.entities.AppUser;

public interface AccountService {
    AppUser addUser (AppUser appUser ,String CnfPass);
    AppRole addROle (AppRole appRole);
    void addRoleToUser (AppUser user,AppRole appRole);
    void removeRoleToUser (String Username,String Role);
    AppUser loadUserByUsername (String username);
}
