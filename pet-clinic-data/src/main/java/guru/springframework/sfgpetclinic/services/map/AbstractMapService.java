package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    private final Map<Long, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to be persisted must not be null");
        }
        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);
        return object;
    }

    public Set<T> findAll() {
        return new HashSet<T>(map.values());
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet()
                .removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        if (map.isEmpty()) {
            return 1L;
        }
        return Collections.max(map.keySet()) + 1;
    }

}
