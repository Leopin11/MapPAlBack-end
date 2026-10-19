package app.service;

import app.domain.Itinerario;
import app.domain.PrestadorLocal;
import app.domain.Reserva;
import app.domain.Turista;
import app.domain.enums.EstadoReserva;
import app.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService (ReservaRepository reservaRepository) {
       this.reservaRepository = reservaRepository;
    }

    public void registrarNuevaReserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double montoTotal, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario ){

        List<Reserva> reservasRegistradas = reservaRepository.reservasRegistradas();

        for (Reserva cadaReserva : reservasRegistradas) {

            if (idReserva == cadaReserva.getIdReserva()) {
                System.out.println("El ID ya existe");
                return;
            }

            if (fechaReserva.toLocalTime().equals(cadaReserva.getFechaReserva().toLocalTime())) {
                System.out.println("Ya existe una reserva en esa hora");
                return;
            }

            if (estado != EstadoReserva.PENDIENTE) {
                System.out.println("Las reservas nuevas deben iniciar en estado PENDIENTE");
                return;
            }

            // Validar monto
            if (montoTotal <= 0) {
                System.out.println("Error: El monto debe ser mayor a cero.");
                return;
            }

            // Validar turista
            if (turista == null) {
                System.out.println("Error: Debe asociar un turista.");
                return;
            }

            // Validar prestador
            if (prestadorLocal == null) {
                System.out.println("Error: Debe asociar un prestador local.");
                return;
            }

            // Validar itinerario
            if (itinerario == null) {
                System.out.println("Error: Debe asociar un itinerario.");
                return;
            }
        }


        Reserva nuevaReserva = new Reserva(idReserva, fechaReserva, estado, montoTotal, turista, prestadorLocal, itinerario);

        reservaRepository.guardarReserva(nuevaReserva);

        System.out.println("Reserva registrada correctamente.");

    }

    public List<Reserva> mostrarReservas(){
        return reservaRepository.reservasRegistradas();
    }
    public Reserva buscarId(int idReserva){

        Reserva reserva = reservaRepository.buscarIdResserva(idReserva);

        if (reserva == null){
            System.out.println("No existe una reserva con el ID: " + idReserva);
            return null;
        }

        return reserva;
    }

    public void eliminarReserva(int idReserva){

        Reserva reserva = reservaRepository.buscarIdResserva(idReserva);

        if (reserva == null){
            System.out.println("No existe una reserva con ese ID");
            return;
        }

        reservaRepository.eliminarReserva(idReserva);

        System.out.println("Reserva eliminada correctamente");


    }
}
