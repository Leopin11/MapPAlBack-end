package app.persistence.repository;

import app.domain.Reserva;
import app.service.outputport.ReservaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class ReservaRepositoryImpl implements ReservaRepository {
    private final List<Reserva> reservas;

    public ReservaRepositoryImpl(List<Reserva> reservas) {
        this.reservas = reservas;
    }


    @Override
    public Reserva guardarReserva(Reserva reserva) {
        this.reservas.add(reserva);
        return reserva;
    }

    @Override
    public List<Reserva> ReservasRegistrados() {
        return Collections.unmodifiableList(this.reservas);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(int idReserva) {
        for (Reserva reserva : this.reservas){
            if (reserva.getIdReserva() == idReserva){
                return Optional.of(reserva);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reserva> actualizarReserva(int idReserva, Reserva reserva) {
        for (int i = 0; i < this.reservas.size(); i++) {
            if (this.reservas.get(i).getIdReserva() == reserva.getIdReserva()) {
                this.reservas.set(i, reserva);
                return Optional.of(reserva);
            }
        }
        return Optional.empty();
    }


    @Override
    public boolean eliminarReservaPorId(int idReserva) {
        return this.reservas.removeIf(reserva -> reserva.getIdReserva()==idReserva);
    }
}
