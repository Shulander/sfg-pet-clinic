package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Specialty extends BaseEntity {
    private String description;
}
