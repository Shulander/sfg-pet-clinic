package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
@AllArgsConstructor
public class VisitJpaService extends AbstractJpaService<Visit, Long> implements VisitService {
    private final VisitRepository visitRepository;

    @Override
    protected CrudRepository<Visit, Long> getRepository() {
        return visitRepository;
    }
}
