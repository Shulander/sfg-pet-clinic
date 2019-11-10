package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
public class PetJpaService extends AbstractJpaService<Pet, Long> implements PetService {
    private final PetRepository petRepository;

    @Override
    protected CrudRepository<Pet, Long> getRepository() {
        return petRepository;
    }
}
