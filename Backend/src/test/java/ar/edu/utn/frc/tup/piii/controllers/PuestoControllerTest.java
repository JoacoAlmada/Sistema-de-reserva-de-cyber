package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.common.PuestoDTO;
import ar.edu.utn.frc.tup.piii.Serivice.PuestoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PuestoController.class)
class PuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PuestoService puestoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PuestoDTO samplePuesto() {
        return PuestoDTO.builder()
                .id(1L)
                .nombre("Puesto 1")
                .tipo("PC")
                .build();
    }

    @Test
    void obtenerPuestos() throws Exception {
        when(puestoService.listarPuestos()).thenReturn(List.of(samplePuesto()));

        mockMvc.perform(get("/api/v1/puestos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Puesto 1"))
                .andExpect(jsonPath("$[0].tipo").value("PC"))
                .andDo(print());
    }
}