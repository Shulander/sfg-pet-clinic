package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -4570651632788061688L;

    private Long id;

}
