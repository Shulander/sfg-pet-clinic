package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
public class VetJpaService extends AbstractJpaService<Vet, Long> implements VetService {
    private final VetRepository vetRepository;

    @Override
    protected CrudRepository<Vet, Long> getRepository() {
        return vetRepository;
    }
}
