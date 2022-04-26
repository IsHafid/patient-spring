package ma.emsi.patient.Security.repo;

import ma.emsi.patient.Security.entities.AppRole;
import ma.emsi.patient.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
