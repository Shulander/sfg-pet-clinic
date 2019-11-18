package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class OwnerServiceMapTest {
    private static final String OWNER_1_LAST_NAME = "Glenanne";
    private static final long OWNER_1_ID = 1L;
    public static final long OWNER_2_ID = 2L;

    @InjectMocks
    private OwnerServiceMap ownerService;

    @Mock
    private PetService petService;

    @BeforeEach
    void setUp() {
        Owner owner = buildOwner(OWNER_1_ID, "Fiona", OWNER_1_LAST_NAME);
        ownerService.save(owner);
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(OWNER_1_ID);

        assertNotNull(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(OWNER_1_ID);

        assertTrue(ownerService.findAll().isEmpty());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(OWNER_1_ID));

        assertTrue(ownerService.findAll().isEmpty());
    }

    @Test
    void saveWithPet() {
        Owner owner = buildOwner(null, "Michael", "Weston");
        Pet mikesPet = buildPet(null, owner);

        Owner ownerPersisted = ownerService.save(owner);

        assertNotNull(ownerPersisted);
        assertNotNull(ownerPersisted.getId());
        verify(petService).save(mikesPet);
    }

    @Test
    void saveWithPersistedPet() {
        Owner owner = buildOwner(null, "Michael", "Weston");
        buildPet(2L, owner);

        Owner ownerPersisted = ownerService.save(owner);

        assertNotNull(ownerPersisted);
        assertNotNull(ownerPersisted.getId());
    }

    @Test
    void saveWithId() {
        Owner owner = buildOwner(OWNER_2_ID, "Michael", "Weston");

        Owner ownerPersisted = ownerService.save(owner);

        assertNotNull(ownerPersisted);
        assertNotNull(ownerPersisted.getId());
        assertEquals(OWNER_2_ID, ownerPersisted.getId());
    }

    @Test
    void saveNullObject() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> ownerService.save(null));
        assertEquals("Object to be persisted must not be null", exception.getMessage());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerService.findByLastName(OWNER_1_LAST_NAME);

        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    private Owner buildOwner(Long id, String name, String lastName) {
        return Owner.builder()
                .id(id)
                .firstName(name)
                .lastName(lastName)
                .address("123 Brickerel")
                .city("Miami")
                .telephone("510 321 4125")
                .build();
    }

    private Pet buildPet(Long id, Owner owner) {
        Pet pet = Pet.builder()
                .id(id)
                .owner(owner)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();

        owner.getPets().add(pet);
        return pet;
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(petService);
    }
}
