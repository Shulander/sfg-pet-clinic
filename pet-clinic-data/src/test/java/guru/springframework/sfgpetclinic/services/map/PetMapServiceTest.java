package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PetMapServiceTest {
    private static PetType petType;
    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private PetServicesMap petMapService;

    private final Long petId = 1L;


    @BeforeAll
    private static void setUpBeforeAll() {
        petType = PetType.builder()
                .id(1L)
                .build();
    }


    @BeforeEach
    void setUp() {
        Pet pet = Pet.builder()
                .id(petId)
                .petType(petType)
                .build();
        petMapService.save(pet);
    }


    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }


    @Test
    void findByIdExistingId() {
        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }


    @Test
    void findByIdNotExistingId() {
        Pet pet = petMapService.findById(5L);

        assertNull(pet);
    }


    @Test
    void findByIdNullId() {
        Pet pet = petMapService.findById(null);

        assertNull(pet);
    }


    @Test
    void saveExistingId() {
        Long id = 2L;
        Pet pet2 = Pet.builder()
                .id(id)
                .petType(petType)
                .build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
    }


    @Test
    void saveDuplicateId() {
        Long id = 1L;
        Pet pet2 = Pet.builder()
                .id(id)
                .petType(petType)
                .build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
    }


    @Test
    void saveNoId() {
        Pet pet = Pet.builder().petType(petType).build();

        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }


    @Test
    void saveNoIdNewPetType() {
        PetType newPetType = PetType.builder().name("snake").build();
        Pet pet = Pet.builder().petType(newPetType).build();

        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
        verify(petTypeService).save(newPetType);
    }


    @Test
    void saveNoPetType() {
        Pet pet = Pet.builder().build();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> petMapService.save(pet));

        assertEquals("Pet Type is required", ex.getMessage());
    }


    @Test
    void saveNullPet() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> petMapService.save(null));

        assertEquals("Object to be persisted must not be null", ex.getMessage());
    }


    @Test
    void deletePet() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }


    @Test
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }


    @Test
    void deleteWithNullId() {
        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }


    @Test
    void deleteNull() {
        petMapService.delete(null);

        assertEquals(1, petMapService.findAll().size());

    }


    @Test
    void deleteByIdCorrectId() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }


    @Test
    void deleteByIdWrongId() {
        petMapService.deleteById(5L);

        assertEquals(1, petMapService.findAll().size());
    }


    @Test
    void deleteByIdNullId() {
        petMapService.deleteById(null);

        assertEquals(1, petMapService.findAll().size());
    }


    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(petTypeService);
    }
}
