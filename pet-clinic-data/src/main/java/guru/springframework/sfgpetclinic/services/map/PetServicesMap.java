package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Repository;

@Repository
class PetServicesMap extends AbstractMapService<Pet, Long> implements PetService {

}
