package app.repository;

import app.domain.Reserva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservaRepository {

    private List<Reserva> reservas;

    public ReservaRepository(Reserva reserva){
        this.reservas = new ArrayList<>();
    }

    public void guardarReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    public List<Reserva> reservasRegistradas(){
        return Collections.unmodifiableList(reservas);
    }

    public Reserva buscarIdResserva(int idReserva){
        for (Reserva reserva : this.reservas){
            if (reserva.getIdReserva() == idReserva){
                return reserva;
            }
        }
        return null;
    }

    public void eliminarReserva(int idReserva){
        this.reservas.removeIf(reserva -> reserva.getIdReserva() == idReserva);
    }
}
