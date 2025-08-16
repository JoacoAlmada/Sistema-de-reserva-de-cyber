package ar.edu.utn.frc.tup.piii.dtos.common;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class videoJuegoDTO {
    private Long id;
    @NotNull
    private String titulo; // obligatorio
    private  String genero;
}
