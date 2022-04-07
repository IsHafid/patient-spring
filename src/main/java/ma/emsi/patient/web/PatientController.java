package ma.emsi.patient.web;

import lombok.AllArgsConstructor;
import ma.emsi.patient.entities.Patient;
import ma.emsi.patient.repo.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepo patientRepo;
    @GetMapping(path="/index")
    public String patient(Model model){
        List<Patient> patients = patientRepo.findAll();
        model.addAttribute("listPatient",patients);
        return "patients";
    }

}
