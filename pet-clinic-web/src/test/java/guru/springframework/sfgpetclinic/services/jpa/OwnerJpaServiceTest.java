package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService ownerService;

    @Test
    void findById() {
        Owner owner = mock(Owner.class);
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        Owner actual = ownerService.findById(1L);

        assertEquals(owner, actual);
        verify(ownerRepository).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

        Owner actual = ownerService.findById(1L);

        assertNull(actual);
        verify(ownerRepository).findById(1L);
    }

    @Test
    void save() {
        Owner owner = mock(Owner.class);
        when(ownerRepository.save(owner)).thenReturn(owner);

        Owner actual = ownerService.save(owner);

        assertEquals(owner, actual);
        verify(ownerRepository).save(owner);
    }

    @Test
    void findAll() {
        Owner owner = mock(Owner.class);
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));

        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertFalse(owners.isEmpty());
        assertEquals(1L, owners.size());
        assertEquals(owner, owners.iterator().next());
        verify(ownerRepository).findAll();
    }

    @Test
    void findAllNone() {
        when(ownerRepository.findAll()).thenReturn(Collections.emptyList());

        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertTrue(owners.isEmpty());
        verify(ownerRepository).findAll();
    }

    @Test
    void deleteById() {
        Long ownerId = 1L;

        ownerService.deleteById(ownerId);

        verify(ownerRepository).deleteById(ownerId);
    }

    @Test
    void delete() {
        Owner owner = mock(Owner.class);

        ownerService.delete(owner);

        verify(ownerRepository).delete(owner);
    }

    @Test
    void findByLastName() {
        String lastName = "lastName";
        Owner owner = mock(Owner.class);
        when(ownerRepository.findByLastName(lastName)).thenReturn(Optional.of(owner));

        Owner actual = ownerService.findByLastName(lastName);

        assertEquals(owner, actual);
        verify(ownerRepository).findByLastName(lastName);
    }

    @Test
    void getRepository() {
        CrudRepository<Owner, Long> actual = ownerService.getRepository();

        assertEquals(ownerRepository, actual);
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(ownerRepository);
    }
}
