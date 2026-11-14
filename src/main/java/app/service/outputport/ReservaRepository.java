package app.service.outputport;

import app.domain.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    public Reserva guardarReserva(Reserva reserva);
    public List<Reserva> ReservasRegistrados();
    public Optional<Reserva> buscarReservaPorId(int idReserva);
    Optional<Reserva> actualizarReserva(int idReserva, Reserva reserva);
    public boolean eliminarReservaPorId(int idReserva);
}
