package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class VetController {

    private final VetService vetService;


    public VetController(VetService vetService) {
        this.vetService = vetService;
    }


    @GetMapping({"/vets", "/vets.html", "/vets/index.html"})
    public String listVets(Model model) {
        log.info("vets mapping");
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
