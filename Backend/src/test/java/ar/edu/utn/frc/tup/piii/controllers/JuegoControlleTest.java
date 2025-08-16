package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.common.videoJuegoDTO;
import ar.edu.utn.frc.tup.piii.Serivice.videojuegoService;
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

@WebMvcTest(JuegoControlle.class)
class JuegoControlleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private videojuegoService videojuegoService;

    @Autowired
    private ObjectMapper objectMapper;

    private videoJuegoDTO sampleJuego() {
        return videoJuegoDTO.builder()
                .id(1L)
                .titulo("FIFA 25")
                .genero("Deporte")
                .build();
    }

    @Test
    void obtenerJuegos() throws Exception {
        when(videojuegoService.listarVideojuego()).thenReturn(List.of(sampleJuego()));

        mockMvc.perform(get("/api/v1/videojuegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("FIFA 25"))
                .andExpect(jsonPath("$[0].genero").value("Deporte"))
                .andDo(print());
    }
}