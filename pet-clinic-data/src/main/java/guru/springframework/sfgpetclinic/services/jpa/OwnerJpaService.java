package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
class OwnerJpaService extends AbstractJpaService<Owner, Long> implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    protected CrudRepository<Owner, Long> getRepository() {
        return ownerRepository;
    }
}
