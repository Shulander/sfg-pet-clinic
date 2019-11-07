package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;
}
