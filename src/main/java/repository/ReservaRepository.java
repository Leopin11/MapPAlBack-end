package repository;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private final List<Reserva> reservas;

    public ReservaRepository(){
        this.reservas = new ArrayList<>();
    }

    public void guardarNuevaReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    public List<Reserva> reservasRegistradas(){
        return this.reservas;
    }

    public Reserva buscarReservaPorid(Integer idReserva){
        for (Reserva reserva : this.reservas){
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
