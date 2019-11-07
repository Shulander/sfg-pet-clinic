package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = "owner")
public class Pet extends BaseEntity {
    private String name;
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
