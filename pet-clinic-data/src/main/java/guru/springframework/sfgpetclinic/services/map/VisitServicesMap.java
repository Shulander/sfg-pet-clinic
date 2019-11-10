package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
class VisitServicesMap extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit visit) {
        if (visit == null
            || visit.getPet() == null || visit.getPet().getId() == null
            || visit.getPet().getOwner() == null || visit.getPet().getOwner().getId() == null) {
            throw new IllegalArgumentException("Object to be persisted must not be null");
        }
        return super.save(visit);
    }
}
