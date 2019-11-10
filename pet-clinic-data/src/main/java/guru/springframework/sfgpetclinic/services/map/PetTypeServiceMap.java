package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

}
