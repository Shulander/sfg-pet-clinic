package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
public class PetTypeJpaService extends AbstractJpaService<PetType, Long> implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    @Override
    protected CrudRepository<PetType, Long> getRepository() {
        return petTypeRepository;
    }
}
