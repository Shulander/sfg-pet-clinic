package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.stereotype.Repository;

@Repository
class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {

}
