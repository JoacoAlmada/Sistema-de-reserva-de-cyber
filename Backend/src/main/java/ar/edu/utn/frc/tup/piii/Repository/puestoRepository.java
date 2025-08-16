package ar.edu.utn.frc.tup.piii.Repository;

import ar.edu.utn.frc.tup.piii.Entity.PuestoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface puestoRepository extends JpaRepository <PuestoJuego, Long> {
}
