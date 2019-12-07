package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

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


    @ResponseBody
    @GetMapping("/api/vets")
    public Set<Vet> listVets() {
        log.info("list vets as json");
        return vetService.findAll();
    }
}
