package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Serivice.ClienteService;
import ar.edu.utn.frc.tup.piii.dtos.common.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteDTO sampleCliente() {
        return ClienteDTO.builder()
                .id(1L)
                .nombreCompleto("Juan Pérez")
                .email("juan.perez@example.com")
                .build();
    }

    @Test
    void obtenerClientes() throws Exception {
        when(clienteService.listarClientes()).thenReturn(List.of(sampleCliente()));

        mockMvc.perform(get("/api/v1/clientes")).andDo(print()) .andExpect(status().isOk());

    }
}
