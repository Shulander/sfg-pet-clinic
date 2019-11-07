package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class PetServicesMap extends AbstractMapService<Pet, Long> implements PetService {
    private final PetTypeService petTypeService;

    @Override
    public Pet save(Pet object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to be persisted must not be null");
        }
        if (object.getPetType() == null) {
            throw new IllegalArgumentException("Pet Type is required");
        }

        object.setPetType(petTypeService.save(object.getPetType()));

        return super.save(object);
    }
}
