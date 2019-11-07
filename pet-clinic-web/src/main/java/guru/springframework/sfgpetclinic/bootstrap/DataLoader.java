package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Data Loader started");
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

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        vetService.save(vet1);
        log.info("Saved vet 1: {}", vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .build();
        vetService.save(vet2);
        log.info("Saved vet 2: {}", vet2);

        log.info("Loaded Vets...");
    }
}
