package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();


    public Pet getPet(String name) {
        return getPet(name, false);
    }


    public Pet getPet(final String name, boolean ignoreNew) {
        String lowerCaseName = name.toLowerCase();

        return pets.stream()
                .filter(pet -> !ignoreNew || !pet.isNew())
                .filter(pet -> pet.getName().toLowerCase().equals(lowerCaseName))
                .findFirst()
                .orElse(null);
    }
}
