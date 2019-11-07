package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Repository;

@Repository
class OwnerServicesMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Owner findByLastName(String lastName) {
        return findAll()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
