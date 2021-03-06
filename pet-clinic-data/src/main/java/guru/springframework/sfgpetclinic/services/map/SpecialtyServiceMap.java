package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {

}
