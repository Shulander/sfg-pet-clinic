package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Repository;

@Repository
class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

}
