package app.service.outputport;

import app.domain.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
     Reserva guardarReserva(Reserva reserva);
     List<Reserva> ReservasRegistrados();
     Optional<Reserva> buscarReservaPorId(int idReserva);
     Optional<Reserva> actualizarReserva(int idReserva, Reserva reserva);
     boolean eliminarReservaPorId(int idReserva);
}
