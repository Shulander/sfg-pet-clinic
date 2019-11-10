package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Data Loader started");
        if (petTypeService.findAll().isEmpty()) {
            loadData();
        }
        log.info("Data loader finished!");
    }

    private void loadData() {
        PetType dogType = PetType.builder().name("Dog").build();
        PetType savedDogType = petTypeService.save(dogType);

        PetType catType = PetType.builder().name("Cat").build();
        PetType savedCatType = petTypeService.save(catType);
        log.info("Loaded PetType...");


        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("510 321 4125")
                .build();

        Pet mikesPet = Pet.builder()
                .petType(savedDogType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);
        log.info("Saved owner 1: {}", owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("510 321 4125")
                .build();

        Pet fionasPet = Pet.builder()
                .petType(savedCatType)
                .owner(owner2)
                .birthDate(LocalDate.now().minusDays(30))
                .name("Arya")
                .build();
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);
        log.info("Saved owner 2: {}", owner2);

        log.info("Loaded Owners...");

        Specialty radiologySpecialty = Specialty.builder().description("Radiology").build();
        Specialty savedRadiologySpecialty = specialtyService.save(radiologySpecialty);
        Specialty surgerySpecialty = Specialty.builder().description("Surgery").build();
        Specialty savedSurgerySpecialty = specialtyService.save(surgerySpecialty);
        Specialty dentistrySpecialty = Specialty.builder().description("Dentistry").build();
        Specialty savedDentistrySpecialty = specialtyService.save(dentistrySpecialty);
        log.info("Loaded Specialties...");

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        vet1.getSpecialties().add(savedRadiologySpecialty);
        vet1.getSpecialties().add(savedSurgerySpecialty);
        vetService.save(vet1);
        log.info("Saved vet 1: {}", vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .build();
        vet2.getSpecialties().add(savedSurgerySpecialty);
        vet2.getSpecialties().add(savedDentistrySpecialty);
        vetService.save(vet2);
        log.info("Saved vet 2: {}", vet2);

        log.info("Loaded Vets...");

        Visit catVisit = Visit.builder()
                .date(LocalDate.now().plusDays(30))
                .description("Sneezy Kitty")
                .pet(fionasPet)
                .build();
        visitService.save(catVisit);
        fionasPet.setVisits(Collections.singleton(catVisit));
        log.info("Loaded Visit...");
    }
}
