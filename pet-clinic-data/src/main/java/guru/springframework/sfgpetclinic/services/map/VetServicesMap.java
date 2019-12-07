package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("map")
@AllArgsConstructor
class VetServicesMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    @Override
    public Vet save(Vet object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to be persisted must not be null");
        }
        if (object.getSpecialities() != null) {
            Set<Specialty> newPets = object.getSpecialities()
                    .stream()
                    .map(this::persistNewObject)
                    .collect(Collectors.toSet());
            object.setSpecialities(newPets);
        }
        return super.save(object);
    }

    private Specialty persistNewObject(Specialty specialty) {
        return specialty.getId() == null ? specialtyService.save(specialty) : specialty;
    }
}
