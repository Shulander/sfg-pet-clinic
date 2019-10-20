package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CrudServices<T, ID> {
    T findById(ID id);

    T save(T object);

    Set<T> findAll();
}
