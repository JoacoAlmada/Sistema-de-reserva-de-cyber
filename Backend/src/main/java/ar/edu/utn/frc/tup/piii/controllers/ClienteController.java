package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.Serivice.ClienteService;
import ar.edu.utn.frc.tup.piii.dtos.common.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
