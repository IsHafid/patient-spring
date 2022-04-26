package ma.emsi.patient.Security.repo;

import ma.emsi.patient.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
