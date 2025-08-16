package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Serivice.ReservaService;
import ar.edu.utn.frc.tup.piii.dtos.common.ReservaDTO;
import ar.edu.utn.frc.tup.piii.dtos.common.ReservaPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaDTO>> getAllReservas(
            @RequestParam(name = "cliente_id") Optional<Long> clienteId,
            @RequestParam(name = "videojuego_id") Optional<Long> puestoId,
            @RequestParam(name = "puesto_id") Optional<Long> videojuegoId,
            @RequestParam(name = "fecha") Optional<LocalDateTime> fechaHora

            ) {
        if (clienteId.isPresent()) {
            return ResponseEntity.ok(reservaService.getAllReservaByCliente(clienteId.get()));
        }
        else if (puestoId.isPresent()) {
            return ResponseEntity.ok(reservaService.getAllReservaByPuesto(puestoId.get()));
        }
        else if (videojuegoId.isPresent()) {
            return ResponseEntity.ok(reservaService.getAllReservaByJuego(videojuegoId.get()));
        }
        else if (fechaHora.isPresent()) {
            return ResponseEntity.ok(reservaService.getAllReservaByFecha(fechaHora.get()));
        }
        else{
            return ResponseEntity.ok(reservaService.listarReserva());
        }
    }
    @PostMapping("/reservas")
    public ResponseEntity<ReservaDTO> createTurno(@RequestBody ReservaPostDTO reserva) {
        return ResponseEntity.ok(reservaService.crearReserva(reserva));
    }
}
