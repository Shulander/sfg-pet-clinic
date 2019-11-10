package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
public class SpecialtyJpaService extends AbstractJpaService<Specialty, Long> implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    @Override
    protected CrudRepository<Specialty, Long> getRepository() {
        return specialtyRepository;
    }
}
