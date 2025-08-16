package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Serivice.PuestoService;
import ar.edu.utn.frc.tup.piii.dtos.common.PuestoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PuestoController {
    @Autowired
    private PuestoService puestoService;

    @GetMapping("/puestos")
    public ResponseEntity<List<PuestoDTO>> obtenerPuestos() {
        return ResponseEntity.ok(puestoService.listarPuestos());
    }
}
