package app.service.outputport;

import app.domain.Reserva;
import app.domain.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    public Reserva guardarReserva(Reserva reserva);
    public List<Reserva> ReservasRegistrados();
    public Optional<Reserva> buscarReservaPorId(int idReserva);
    public Optional<Reserva> actualizarReserva(Reserva reserva);
    public boolean eliminarReservaPorId(int idReserva);
}
