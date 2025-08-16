package ar.edu.utn.frc.tup.piii.dtos.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuestoDTO {
    private   Long id;
    private String nombre;
    private   String tipo;
}
