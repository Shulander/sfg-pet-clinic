package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("pet")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/{petId}/edit")
    public String updatePetForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);

        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet, @PathVariable("petId") Long petId,
                                       @PathVariable("ownerId") Long ownerId, Model model,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        // TODO: 12/1/19 Check this latter
        pet.setId(petId);
        Pet savedPet = petService.save(pet);

        return "redirect:/owners/" + ownerId;
    }

    @GetMapping("/pets/new")
    public String newPetForm(Model model) {
        model.addAttribute("pet", Pet.builder().build());

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processNewPetForm(@Valid Pet pet, @PathVariable("ownerId") Long ownerId, Model model,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        // TODO: 12/1/19 Check this latter
        Pet savedPet = petService.save(pet);

        return "redirect:/owners/" + ownerId;
    }

}
