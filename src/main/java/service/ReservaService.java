package service;

import domain.Itinerario;
import domain.Reserva;
import repository.ItinerarioRepository;
import repository.ReservaRepository;

import java.util.List;

public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService (ReservaRepository reservaRepository) {
       this.reservaRepository = reservaRepository;

    }

    public void registrarReserva(Reserva reserva){
        if (reserva == null){
            System.out.println("La reserva no puede estar nula");
            return;
        }
        System.out.println("Reserva agregada con existo");
        reservaRepository.guardarReserva(reserva);
    }

    public List<Reserva> mostrarReservas(){
        return reservaRepository.reservasRegistradas();
    }
    public Reserva buscarId(int idReserva){
        return reservaRepository.buscarIdResserva(idReserva);
    }

    public void eliminarReserva(int idReserva){
        reservaRepository.eliminarReserva(idReserva);
    }
}
