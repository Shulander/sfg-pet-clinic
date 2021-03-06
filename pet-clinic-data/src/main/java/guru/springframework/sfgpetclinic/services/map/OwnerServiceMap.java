package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("map")
@RequiredArgsConstructor
class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
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
                    .map(this::persistNewObject)
                    .collect(Collectors.toSet());
            object.setPets(newPets);
        }
        return super.save(object);
    }

    private Pet persistNewObject(Pet pet) {
        return pet.getId() == null ? petService.save(pet) : pet;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return findAll()
                .stream()
                .filter(owner -> owner.getLastName() != null && lastName != null)
                .filter(owner -> owner.getLastName().toUpperCase().contains(lastName.toUpperCase()))
                .collect(Collectors.toList());
    }
}
