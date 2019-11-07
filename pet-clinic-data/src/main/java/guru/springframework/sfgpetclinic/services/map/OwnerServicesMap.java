package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class OwnerServicesMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetService petService;

    @Override
    public Owner save(Owner object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to be persisted must not be null");
        }
        if (object.getPets() != null) {
            Set<Pet> newPets = object.getPets()
                    .stream()
                    .peek(pet -> pet.setOwner(object))
                    .map(petService::save)
                    .collect(Collectors.toSet());
            object.setPets(newPets);
        }
        return super.save(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return findAll()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
