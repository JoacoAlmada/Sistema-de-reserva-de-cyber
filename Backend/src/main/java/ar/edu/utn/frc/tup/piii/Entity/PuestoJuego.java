package ar.edu.utn.frc.tup.piii.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "puesto_juego")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuestoJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;
    @Column
    @NotNull
    private String nombre; // obligatorio
    @Column
    private   String tipo;   // PC, Consola, etc.
}
