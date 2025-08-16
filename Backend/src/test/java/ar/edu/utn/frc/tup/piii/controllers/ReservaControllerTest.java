package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import ar.edu.utn.frc.tup.piii.Serivice.ReservaService;
import ar.edu.utn.frc.tup.piii.dtos.common.ClienteDTO;
import ar.edu.utn.frc.tup.piii.dtos.common.PuestoDTO;
import ar.edu.utn.frc.tup.piii.dtos.common.ReservaDTO;
import ar.edu.utn.frc.tup.piii.dtos.common.ReservaPostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Autowired
    private ObjectMapper objectMapper;

    private ReservaDTO sampleReserva() {
        return ReservaDTO.builder()
                .id(1L)
                .cliente(Cliente.builder()
                        .id(1L)
                        .nombreCompleto("Juan Pérez")
                        .email("juan.perez@example.com")
                        .build())
                .videojuego(VideoJuego.builder()
                        .id(2L)
                        .titulo("FIFA 25")
                        .genero("Deportes")
                        .build())
                .puesto(PuestoJuego.builder()
                        .id(3L)
                        .nombre("Puesto 3")
                        .tipo("Consola")
                        .build())
                .fechaHora(LocalDateTime.of(2025, 7, 3, 14, 0))
                .duracionMinutos(60)
                .observaciones("Sin preferencias")
                .build();
    }

    @Test
    void getAllReservas() throws Exception {
        when(reservaService.listarReserva()).thenReturn(List.of(sampleReserva()));

        mockMvc.perform(get("/api/v1/reservas"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getReservasByCliente() throws Exception {
        when(reservaService.getAllReservaByCliente(1L)).thenReturn(List.of(sampleReserva()));

        mockMvc.perform(get("/api/v1/reservas?cliente_id=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getReservasByPuesto() throws Exception {
        when(reservaService.getAllReservaByPuesto(3L)).thenReturn(List.of(sampleReserva()));

        mockMvc.perform(get("/api/v1/reservas?videojuego_id=3"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getReservasByVideojuego() throws Exception {
        when(reservaService.getAllReservaByJuego(2L)).thenReturn(List.of(sampleReserva()));

        mockMvc.perform(get("/api/v1/reservas?puesto_id=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createTurno() throws Exception {
        ReservaPostDTO request = ReservaPostDTO.builder()
                .clienteId(1L)
                .videojuegoId(2L)
                .puestoId(3L)
                .fechaHora(LocalDateTime.of(2025, 7, 3, 14, 0))
                .duracionMinutos(60)
                .observaciones("Sin preferencias")
                .build();

        when(reservaService.crearReserva(request)).thenReturn(sampleReserva());

        mockMvc.perform(post("/api/v1/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}