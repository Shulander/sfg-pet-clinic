package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


abstract class AbstractJpaService<T extends BaseEntity, ID> {

    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    public T save(T object) {
        return getRepository().save(object);
    }

    public Set<T> findAll() {
        Iterable<T> all = getRepository().findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    public void delete(T object) {
        getRepository().delete(object);
    }

    protected abstract CrudRepository<T, ID> getRepository();

}
