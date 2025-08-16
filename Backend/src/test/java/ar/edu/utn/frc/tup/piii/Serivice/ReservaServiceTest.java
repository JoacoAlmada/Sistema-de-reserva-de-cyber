package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.*;
import ar.edu.utn.frc.tup.piii.Repository.reservaRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private reservaRepository reservaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private videojuegoService videojuegoService;

    @Mock
    private PuestoService puestoService;

    @Mock
    private ClienteService clienteService;

    @Test
    void listarReserva() {
        List<Reserva> reservas = List.of(new Reserva(), new Reserva());
        List<ReservaDTO> reservasDTO = List.of(new ReservaDTO(), new ReservaDTO());

        when(reservaRepository.findAll()).thenReturn(reservas);
        when(modelMapper.map(reservas, new TypeToken<List<ReservaDTO>>() {}.getType())).thenReturn(reservasDTO);

        List<ReservaDTO> result = reservaService.listarReserva();
        assertEquals(2, result.size());
    }

    @Test
    void getAllReservaByCliente_ok() {
        List<Reserva> reservas = List.of(new Reserva());
        List<ReservaDTO> reservasDTO = List.of(new ReservaDTO());

        when(reservaRepository.findAllByClienteId(1L)).thenReturn(reservas);
        when(modelMapper.map(reservas, new TypeToken<List<ReservaDTO>>() {}.getType())).thenReturn(reservasDTO);

        List<ReservaDTO> result = reservaService.getAllReservaByCliente(1L);
        assertEquals(1, result.size());
    }

    @Test
    void getAllReservaByCliente_empty_throws() {
        when(reservaRepository.findAllByClienteId(1L)).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> reservaService.getAllReservaByCliente(1L));
    }

    @Test
    void getAllReservaByPuesto_ok() {
        List<Reserva> reservas = List.of(new Reserva());
        List<ReservaDTO> reservasDTO = List.of(new ReservaDTO());

        when(reservaRepository.findAllByPuestoId(1L)).thenReturn(reservas);
        when(modelMapper.map(reservas, new TypeToken<List<ReservaDTO>>() {}.getType())).thenReturn(reservasDTO);

        List<ReservaDTO> result = reservaService.getAllReservaByPuesto(1L);
        assertEquals(1, result.size());
    }

    @Test
    void getAllReservaByPuesto_empty_throws() {
        when(reservaRepository.findAllByPuestoId(1L)).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> reservaService.getAllReservaByPuesto(1L));
    }

    @Test
    void getAllReservaByJuego_ok() {
        List<Reserva> reservas = List.of(new Reserva());
        List<ReservaDTO> reservasDTO = List.of(new ReservaDTO());

        when(reservaRepository.findAllByvideojuegoId(1L)).thenReturn(reservas);
        when(modelMapper.map(reservas, new TypeToken<List<ReservaDTO>>() {}.getType())).thenReturn(reservasDTO);

        List<ReservaDTO> result = reservaService.getAllReservaByJuego(1L);
        assertEquals(1, result.size());
    }

    @Test
    void getAllReservaByJuego_empty_throws() {
        when(reservaRepository.findAllByvideojuegoId(1L)).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> reservaService.getAllReservaByJuego(1L));
    }

    @Test
    void getAllReservaByFecha_ok() {
        LocalDateTime fecha = LocalDateTime.of(2025, 7, 4, 15, 0);
        List<Reserva> reservas = List.of(new Reserva());
        List<ReservaDTO> reservasDTO = List.of(new ReservaDTO());

        when(reservaRepository.findAllByFechaHora(fecha)).thenReturn(reservas);
        when(modelMapper.map(reservas, new TypeToken<List<ReservaDTO>>() {}.getType())).thenReturn(reservasDTO);

        List<ReservaDTO> result = reservaService.getAllReservaByFecha(fecha);
        assertEquals(1, result.size());
    }

    @Test
    void getAllReservaByFecha_empty_throws() {
        LocalDateTime fecha = LocalDateTime.of(2025, 7, 4, 15, 0);
        when(reservaRepository.findAllByFechaHora(fecha)).thenReturn(List.of());
        assertThrows(EntityNotFoundException.class, () -> reservaService.getAllReservaByFecha(fecha));
    }

    @Test
    void crearReserva_ok() {
        ReservaPostDTO post = ReservaPostDTO.builder()
                .clienteId(1L)
                .puestoId(2L)
                .videojuegoId(3L)
                .fechaHora(LocalDateTime.of(2025, 7, 4, 15, 0))
                .duracionMinutos(60)
                .observaciones("obs")
                .build();

        Cliente cliente = new Cliente();
        PuestoJuego puesto = new PuestoJuego();
        VideoJuego juego = new VideoJuego();
        Reserva reservaEntity = new Reserva();

        when(clienteService.getClienteById(1L)).thenReturn(Optional.of(new ClienteDTO()));
        when(puestoService.getPuestoById(2L)).thenReturn(Optional.of(new PuestoDTO()));
        when(videojuegoService.getJuegoById(3L)).thenReturn(Optional.of(new videoJuegoDTO()));
        when(modelMapper.map(any(ClienteDTO.class), eq(Cliente.class))).thenReturn(cliente);
        when(modelMapper.map(any(PuestoDTO.class), eq(PuestoJuego.class))).thenReturn(puesto);
        when(modelMapper.map(any(videoJuegoDTO.class), eq(VideoJuego.class))).thenReturn(juego);

        when(reservaRepository.findByCliente_Id(1L)).thenReturn(List.of());
        when(reservaRepository.findAllByPuestoId(2L)).thenReturn(List.of());
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaEntity);
        when(modelMapper.map(reservaEntity, ReservaDTO.class)).thenReturn(new ReservaDTO());

        ReservaDTO result = reservaService.crearReserva(post);
        assertNotNull(result);
    }

    @Test
    void crearReserva_duracionInvalida_throws() {
        ReservaPostDTO post = ReservaPostDTO.builder()
                .duracionMinutos(25)
                .fechaHora(LocalDateTime.of(2025, 7, 4, 15, 0))
                .build();

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservaService.crearReserva(post));
        assertTrue(ex.getReason().contains("Duración inválida"));
    }

    @Test
    void crearReserva_fueraDeHorario_throws() {
        ReservaPostDTO post = ReservaPostDTO.builder()
                .duracionMinutos(60)
                .fechaHora(LocalDateTime.of(2025, 7, 4, 23, 0))
                .build();

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservaService.crearReserva(post));
        assertTrue(ex.getReason().contains("Horario inválido"));
    }

    @Test
    void crearReserva_clienteYaTieneReserva_throws() {
        ReservaPostDTO post = ReservaPostDTO.builder()
                .clienteId(1L)
                .puestoId(2L)
                .videojuegoId(3L)
                .fechaHora(LocalDateTime.of(2025, 7, 4, 15, 0))
                .duracionMinutos(60)
                .build();

        Reserva r = new Reserva();
        r.setFechaHora(LocalDateTime.of(2025, 7, 4, 10, 0));
        when(reservaRepository.findByCliente_Id(1L)).thenReturn(List.of(r));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservaService.crearReserva(post));
        assertTrue(ex.getReason().contains("ya tiene una reserva"));
    }

    @Test
    void crearReserva_solapamientoPuesto_throws() {
        ReservaPostDTO post = ReservaPostDTO.builder()
                .clienteId(1L)
                .puestoId(2L)
                .videojuegoId(3L)
                .fechaHora(LocalDateTime.of(2025, 7, 4, 15, 0))
                .duracionMinutos(60)
                .build();

        when(reservaRepository.findByCliente_Id(1L)).thenReturn(List.of());
        Reserva r = new Reserva();
        r.setFechaHora(LocalDateTime.of(2025, 7, 4, 15, 30));
        when(reservaRepository.findAllByPuestoId(2L)).thenReturn(List.of(r));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservaService.crearReserva(post));
        assertTrue(ex.getReason().contains("solapa"));
    }
}