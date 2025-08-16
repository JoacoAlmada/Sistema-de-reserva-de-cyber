package ar.edu.utn.frc.tup.piii.Serivice;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import ar.edu.utn.frc.tup.piii.Entity.Reserva;
import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import ar.edu.utn.frc.tup.piii.Repository.reservaRepository;
import ar.edu.utn.frc.tup.piii.dtos.common.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private reservaRepository ReservaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private videojuegoService VideojuegoService;
    @Autowired
    private PuestoService PuestoService;
    @Autowired
    private ClienteService ClienteService;

    public List<ReservaDTO> listarReserva( ) {
        List<Reserva> turno = ReservaRepository.findAll();
        return modelMapper.map(turno, new TypeToken<List<ReservaDTO>>() {}.getType());
    }

    public List<ReservaDTO> getAllReservaByCliente( Long clienteId ) {
        List<Reserva> turno = ReservaRepository.findAllByClienteId(clienteId);
        if (!turno.isEmpty()) {
            return modelMapper.map(turno, new TypeToken<List<ReservaDTO>>() {}.getType());
        }
        throw new EntityNotFoundException();
    }

    public List<ReservaDTO> getAllReservaByPuesto( Long puestoId ) {
        List<Reserva> turno = ReservaRepository.findAllByPuestoId(puestoId);
        if (!turno.isEmpty()) {
            return modelMapper.map(turno, new TypeToken<List<ReservaDTO>>() {}.getType());
        }
        throw new EntityNotFoundException();
    }

    public List<ReservaDTO> getAllReservaByJuego (Long videojuegoId ) {
        List<Reserva> turno = ReservaRepository.findAllByvideojuegoId(videojuegoId);
        if (!turno.isEmpty()) {
            return modelMapper.map(turno, new TypeToken<List<ReservaDTO>>() {}.getType());
        }
        throw new EntityNotFoundException();
    }

    public List<ReservaDTO> getAllReservaByFecha (LocalDateTime fechaHora ) {
        List<Reserva> turno = ReservaRepository.findAllByFechaHora((fechaHora));
        if (!turno.isEmpty()) {
            return modelMapper.map(turno, new TypeToken<List<ReservaDTO>>() {}.getType());
        }
        throw new EntityNotFoundException();
    }


    public ReservaDTO crearReserva(ReservaPostDTO reservaPost) {
        // Validar duración
        List<Integer> duracionesValidas = List.of(30, 60, 90, 120);
        if (!duracionesValidas.contains(reservaPost.getDuracionMinutos())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duración inválida. Solo se permiten 30, 60, 90 o 120 minutos.");
        }

        // Validar horario
        LocalTime hora = reservaPost.getFechaHora().toLocalTime();
        if (hora.isBefore(LocalTime.of(10, 0)) || hora.isAfter(LocalTime.of(22, 0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horario inválido. Solo se permiten reservas entre las 10:00 y las 22:00.");
        }

        // Validar si el cliente ya tiene una reserva ese día
        List<Reserva> reservasCliente = ReservaRepository.findByCliente_Id(reservaPost.getClienteId()).stream().
                filter(reserva ->reserva.getFechaHora().getDayOfMonth()== reservaPost.getFechaHora().getDayOfMonth() ).toList();;
        if (!reservasCliente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente ya tiene una reserva para ese día.");

        }

        // Validar solapamiento en el puesto
        int horario = reservaPost.getFechaHora().getHour();
        List<Reserva> reservaPuesto = ReservaRepository.findAllByPuestoId(reservaPost.getPuestoId()).stream().
                filter(reserva ->reserva.getFechaHora().getHour()== horario ).toList();
        if (!reservaPuesto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una reserva en ese puesto que se solapa con la nueva.");
        }


        // Mapear y guardar
        Reserva reserva = new Reserva();
        reserva.setCliente(modelMapper.map(ClienteService.getClienteById(reservaPost.getClienteId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")), Cliente.class));
        reserva.setPuesto(modelMapper.map(PuestoService.getPuestoById(reservaPost.getPuestoId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Puesto no encontrado")), PuestoJuego.class));
        reserva.setVideojuego(modelMapper.map(VideojuegoService.getJuegoById(reservaPost.getVideojuegoId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Videojuego no encontrado")), VideoJuego.class));
        reserva.setFechaHora(reservaPost.getFechaHora());
        reserva.setDuracionMinutos(reservaPost.getDuracionMinutos());
        reserva.setObservaciones(reservaPost.getObservaciones());

        reserva = ReservaRepository.save(reserva);
        return modelMapper.map(reserva, ReservaDTO.class);
    }
}
