package repository;

import domain.Comercio;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private final List<Comercio.Reserva> reservas;

    public ReservaRepository(){
        this.reservas = new ArrayList<>();
    }

    public void guardarNuevaReserva(Comercio.Reserva reserva){
        this.reservas.add(reserva);
    }

    public List<Comercio.Reserva> reservasRegistradas(){
        return this.reservas;
    }

    public Comercio.Reserva buscarReservaPorid(Integer idReserva){
        for (Comercio.Reserva reserva : this.reservas){
            if (reserva.getIdReserva().equals(idReserva)){
                return reserva;
            }
        }
        return null;
    }

    public void eliminarReserva(Integer idReserva){
        this.reservas.removeIf(reserva -> reserva.getIdReserva().equals(idReserva));
    }
}
