package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    private VetService vetService;
    @InjectMocks
    private VetController vetController;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(vetController)
                .build();
    }


    @Test
    void shouldListVetsAsMVC() throws Exception {
        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        when(vetService.findAll()).thenReturn(Collections.singleton(vet1));

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/index"));
    }


    @Test
    void shouldApiListVetsAsJson() throws Exception {
        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        when(vetService.findAll()).thenReturn(Collections.singleton(vet1));

        mockMvc.perform(get("/api/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Sam")))
                .andExpect(jsonPath("$[0].lastName", is("Axe")));
    }


    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(vetService);
    }
}
