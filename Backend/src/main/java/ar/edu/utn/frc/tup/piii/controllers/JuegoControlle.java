package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Serivice.videojuegoService;
import ar.edu.utn.frc.tup.piii.dtos.common.videoJuegoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JuegoControlle {
    @Autowired
    private videojuegoService VideojuegoService;

    @GetMapping("/videojuegos")
    public ResponseEntity<List<videoJuegoDTO>> obtenerJuegos() {
        return ResponseEntity.ok(VideojuegoService.listarVideojuego());
    }
}
