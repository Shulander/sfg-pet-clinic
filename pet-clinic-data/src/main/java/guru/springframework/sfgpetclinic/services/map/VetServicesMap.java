package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Repository;

@Repository
public class VetServicesMap extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Vet save(Vet object) {
        return save(object.getId(), object);
    }
}
