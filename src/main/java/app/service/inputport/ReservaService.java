package app.service.inputport;

import app.domain.Itinerario;
import app.domain.PrestadorLocal;
import app.domain.Reserva;
import app.domain.Turista;
import app.domain.enums.EstadoReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Optional<Reserva> registrarReserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double montoTotal, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario);
    Optional<Reserva> actualizarReserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double montoTotal, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario);
    List<Reserva> listarReservas();
    Optional<Reserva> buscarReservaPorId(int idReserva);
    boolean eliminarReservaPorId(int idReserva);

}
