package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class Owner extends Person {
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();
}
