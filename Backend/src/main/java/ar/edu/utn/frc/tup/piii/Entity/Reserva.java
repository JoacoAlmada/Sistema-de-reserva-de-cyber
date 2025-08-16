package ar.edu.utn.frc.tup.piii.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reserva")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private   Cliente cliente;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideoJuego videojuego;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "puesto_id", nullable = false)
    private PuestoJuego puesto;
    @NotNull
    @Column
    private LocalDateTime fechaHora;
    @NotNull
    @Column
    private   Integer duracionMinutos;
    @NotNull
    private   String observaciones;
}
