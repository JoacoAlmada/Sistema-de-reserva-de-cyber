package ar.edu.utn.frc.tup.piii.Repository;

import ar.edu.utn.frc.tup.piii.Entity.Cliente;
import ar.edu.utn.frc.tup.piii.Entity.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface videojuegoRepository extends JpaRepository<VideoJuego, Long> {
}
