package ar.edu.utn.frc.tup.piii.dtos.common;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    @NotNull
    private Cliente cliente;       // obligatorio
    @NotNull
    private VideoJuego videojuego; // obligatorio
    @NotNull
    private PuestoJuego puesto;    // obligatorio
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora; // obligatorio
    @NotNull
    private   Integer duracionMinutos; // obligatorio
    private   String observaciones;
}
