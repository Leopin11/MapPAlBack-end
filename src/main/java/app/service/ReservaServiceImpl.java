package app.service;

import app.domain.Itinerario;
import app.domain.PrestadorLocal;
import app.domain.Reserva;
import app.domain.Turista;
import app.domain.enums.EstadoReserva;

import app.service.inputport.ReservaService;
import app.service.outputport.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
       this.reservaRepository = reservaRepository;
    }


    @Override
    public Optional<Reserva> registrarReserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double montoTotal, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario) {
        if (reservaRepository.buscarReservaPorId(idReserva).isPresent()){
            return Optional.empty();
        }
        Reserva nuevaReserva = new Reserva(idReserva, fechaReserva, estado, montoTotal, turista, prestadorLocal, itinerario);
        return Optional.of(reservaRepository.guardarReserva(nuevaReserva));
    }

    @Override
    public Optional<Reserva> actualizarReserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double montoTotal, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario) {
        Optional<Reserva> existente = reservaRepository.buscarReservaPorId(idReserva);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Reserva actualizada = existente.get();
        actualizada.setFechaReserva(fechaReserva);
        actualizada.setEstado(estado);
        actualizada.setMontoTotal(montoTotal);
        actualizada.setTurista(turista);
        actualizada.setPrestadorLocal(prestadorLocal);
        actualizada.setItinerario(itinerario);

        return reservaRepository.actualizarReserva(idReserva, actualizada);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.ReservasRegistrados();
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(int idReserva) {
        return reservaRepository.buscarReservaPorId(idReserva);
    }

    @Override
    public boolean eliminarReservaPorId(int idReserva) {
        return reservaRepository.eliminarReservaPorId(idReserva);
    }
}
