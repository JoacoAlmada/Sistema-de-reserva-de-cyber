package ar.edu.utn.frc.tup.piii.Repository;

import ar.edu.utn.frc.tup.piii.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByClienteId(Long clienteId);
    List<Reserva> findAllByPuestoId(Long puestoId);
    List<Reserva> findAllByvideojuegoId(Long videojuegoId);

    List<Reserva> findByCliente_Id(Long clienteId);

    List<Reserva> findAllByFechaHora(LocalDateTime fechaHora);
}
