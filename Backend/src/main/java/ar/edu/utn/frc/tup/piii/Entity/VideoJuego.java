package ar.edu.utn.frc.tup.piii.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "videojuegos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @Column
    @NotNull
     private String titulo; // obligatorio
    @Column
    private  String genero;
}
