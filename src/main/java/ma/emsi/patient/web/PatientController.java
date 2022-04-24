package ma.emsi.patient.web;

import lombok.AllArgsConstructor;
import ma.emsi.patient.entities.Patient;
import ma.emsi.patient.repo.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepo patientRepo;
    @GetMapping(path="/index")
    public String patient(Model model ,
                          @RequestParam(name = "page",defaultValue="0")int page ,
                          @RequestParam(name = "size",defaultValue="5")int size,
                          @RequestParam(name = "keyword",defaultValue="")String keyword
    ){
        Page<Patient> Pagepatients = patientRepo.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient",Pagepatients);
        model.addAttribute("pages",new int[Pagepatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword,int page){
        patientRepo.deleteById(id);
        return "redirect:/index?page="+page+"&keyword"+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> lispatient(){
        return patientRepo.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult
            ,@RequestParam(defaultValue = "") String keyword
            ,@RequestParam(defaultValue = "0")  int page){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepo.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient= patientRepo.findById(id).get();
        if (patient==null) throw new RuntimeException("Patient Introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }
}

