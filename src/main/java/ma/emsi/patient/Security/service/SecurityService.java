package ma.emsi.patient.Security.service;

import ma.emsi.patient.Security.entities.AppRole;
import ma.emsi.patient.Security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);

}