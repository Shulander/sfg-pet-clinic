package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
