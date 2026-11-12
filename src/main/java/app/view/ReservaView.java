package app.view;

import app.domain.*;
import app.domain.enums.EstadoReserva;
import app.service.inputport.ReservaService;
import app.service.inputport.TuristaService;
import java.time.LocalDateTime;
import java.util.Optional;

public class ReservaView {
    private final ReservaService reservaService;
    private final TuristaService turistaService;
    private final PrestadorLocalService prestadorLocalService;
    private final ItinerarioService itinerarioService;
    private final Consola consola;

    public ReservaView(ReservaService reservaService, TuristaService turistaService, PrestadorLocalService prestadorLocalService, ItinerarioService itinerarioService, Consola consola) {
        this.reservaService = reservaService;
        this.turistaService = turistaService;
        this.prestadorLocalService = prestadorLocalService;
        this.itinerarioService = itinerarioService;
        this.consola = consola;
    }

    private void guardar(){
        System.out.println("===== Datos de la Reserva =====");

        int idReserva = consola.leerEntero("Ingrese el id de la Reserva:");
        LocalDateTime fechaReserva = consola.leerFecha("Ingrese la fecha de la reserva").atStartOfDay();
        EstadoReserva estadoReserva = EstadoReserva.PENDIENTE;
        double montoTotal = consola.leerEntero("Ingrese el monto de la Reserva:");
        int idTurista = consola.leerIdTurista("Ingrese el ID del turista");
        int prestadorLocal = consola.leerEntero("Ingrese el ID del Prestador Local");
        int itinerario = consola.leerEntero("Ingrese el ID del Itinerario");

        Optional<Turista> turistaBusqueda = turistaService.buscarTuristaPorId(idTurista);

        if(turistaBusqueda.isEmpty()) {
            System.out.println("No existe el turista con ese ID");
            return;
        }
        Turista turista = turistaBusqueda.get();

        Optional<Reserva> registrado = reservaService.registrarReserva(idReserva,fechaReserva,EstadoReserva.PENDIENTE,montoTotal,turista,prestadorLocal,itinerario);

        if (registrado.isPresent()) {
            System.out.println("Reserva registrada con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe una Reserva con el ID " + idReserva);
        }
    }
}
