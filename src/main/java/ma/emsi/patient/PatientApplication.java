package ma.emsi.patient;

import ma.emsi.patient.entities.Patient;
import ma.emsi.patient.repo.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientApplication.class, args);

		}

	@Bean
	CommandLineRunner commandLineRunner(PatientRepo patientrepo) {
			return args -> {
				patientrepo.save(new Patient(null,"Ismail",new Date(),false,12));
				patientrepo.save(new Patient(null,"Hatim",new Date(),true,15));
				patientrepo.save(new Patient(null,"Hafid",new Date(),false,18));
				patientrepo.save(new Patient(null,"Errimy",new Date(),false,19));

				patientrepo.findAll().forEach(p->{
					System.out.println(p.getNom());
				});

			};

	}



}
