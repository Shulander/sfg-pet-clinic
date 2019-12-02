package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/index", "/index.html", "/list"})
    public String listOwners(Model model) {
        Set<Owner> all = ownerService.findAll();
        model.addAttribute("selections", all);
        return "owners/ownersList";
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model) {
        log.info("Find Owners");
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            // empty string signifies broadest possible search
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName());
        if (CollectionUtils.isEmpty(owners)) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            Owner foundOwner = owners.iterator().next();
            return "redirect:/owners/" + foundOwner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Owner owner = ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }

    @GetMapping("/new")
    public String newOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processNewOwnerForm(@Valid Owner owner, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner persistedOwner = ownerService.save(owner);

        model.addAttribute("owner", persistedOwner);

        return "redirect:/owners/" + persistedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String updateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, @PathVariable("ownerId") Long ownerId, Model model,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        owner.setId(ownerId);
        Owner persistedOwner = ownerService.save(owner);
        model.addAttribute("owner", persistedOwner);

        return "redirect:/owners/" + persistedOwner.getId();
    }

}
