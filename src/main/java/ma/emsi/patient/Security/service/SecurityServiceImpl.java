package ma.emsi.patient.Security.service;

import ma.emsi.patient.Security.entities.AppRole;
import ma.emsi.patient.Security.entities.AppUser;
import ma.emsi.patient.Security.repo.AppRoleRepo;
import ma.emsi.patient.Security.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepo appUserRepository;
    private AppRoleRepo appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password Doesn't Match!");
        String hashedPWD= passwordEncoder.encode(password);
        AppUser appUser= new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("Role "+roleName+" Already exists!");
        appRole= new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User Not Found.");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role Not Found.");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User Not Found.");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role Not Found.");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}