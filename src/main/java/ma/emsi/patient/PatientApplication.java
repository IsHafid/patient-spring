package ma.emsi.patient;

import ma.emsi.patient.Security.service.SecurityService;
import ma.emsi.patient.entities.Patient;
import ma.emsi.patient.repo.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientApplication.class, args);

		}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner saveUsers(SecurityService SS){
		return args -> {
		SS.saveNewUser("ABCD","ABCD","ABCD");
		SS.saveNewUser("EFG","EFG","EFG");
		SS.saveNewRole("USER","");
		SS.saveNewRole("ADMIN","");
		SS.addRoleToUser("ABCD","ADMIN");
		SS.addRoleToUser("EFG","USER");
		SS.addRoleToUser("ABCD","USER");


		};
	}

}
