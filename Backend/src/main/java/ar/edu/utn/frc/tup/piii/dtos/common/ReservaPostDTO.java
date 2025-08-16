package ar.edu.utn.frc.tup.piii.dtos.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaPostDTO {
    @JsonProperty("cliente_id")
    private Long clienteId;
    @JsonProperty("videojuego_id")
    private Long videojuegoId;
    @JsonProperty("puesto_id")
    private Long puestoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora;
    private   Integer duracionMinutos;
    private   String observaciones;
}
