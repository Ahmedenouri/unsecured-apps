package ma.enset.hospitalapp.security.service;

import ma.enset.hospitalapp.security.entities.AppRole;
import ma.enset.hospitalapp.security.entities.AppUser;

public interface AccountService {
    AppUser addUser (String username,String password,String email ,String CnfPass);
    AppRole addRole (String role);
    void addRoleToUser (String username,String role);
    void removeRoleToUser (String Username,String Role);
    AppUser loadUserByUsername (String username);
}
