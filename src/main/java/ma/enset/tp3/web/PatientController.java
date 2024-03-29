package ma.enset.tp3.web;


import jakarta.validation.Valid;
import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/patients")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw
                        ) {

        Page<Patient> pagePatients = patientRepository.findByNameContains( kw , PageRequest.of(page,size));

        model.addAttribute("patients", pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        model.addAttribute("repository", patientRepository);
        model.addAttribute("page", page);
        return "patients";
    }


    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/patients?keyword="+keyword+"&page="+page;
    }

    @GetMapping("formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient" , new Patient());
        return  "formPatients";
    }

    @GetMapping("editPatients")
    public String editPatients(Model model, Long id, String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient Introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatients";
    }

    @PostMapping("save")
    public String save(Model model,
                       @Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "keyword", defaultValue = "") String kw
    ){
        if (bindingResult.hasErrors()) return  "formPatients";
        patientRepository.save(patient);
        return "redirect:/patients?keyword="+kw+"&page="+page;
    }


}
