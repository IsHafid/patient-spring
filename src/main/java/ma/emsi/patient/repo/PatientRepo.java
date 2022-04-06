package ma.emsi.patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import  ma.emsi.patient.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient,Long> {



}
