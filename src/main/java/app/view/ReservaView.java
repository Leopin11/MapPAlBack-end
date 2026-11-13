package app.view;

import app.domain.Itinerario;
import app.domain.PrestadorLocal;
import app.domain.Reserva;
import app.domain.Turista;
import app.domain.enums.EstadoReserva;
import app.service.ItinerarioService;
import app.service.inputport.PrestadorLocalService;
import app.service.inputport.ReservaService;
import app.service.inputport.TuristaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservaView {
    private final ReservaService reservaService;
    private final TuristaService turistaService;
    private final PrestadorLocalService prestadorLocalService;
    private final ItinerarioService itinerarioService;
    private final Consola consola;

    public ReservaView(ReservaService reservaService, TuristaService turistaService,
                       PrestadorLocalService prestadorLocalService, ItinerarioService itinerarioService,
                       Consola consola) {
        this.reservaService = reservaService;
        this.turistaService = turistaService;
        this.prestadorLocalService = prestadorLocalService;
        this.itinerarioService = itinerarioService;
        this.consola = consola;
    }

    public void mostrarMenuReservas() {
        int opcion;
        do {
            System.out.println("""
                    ========== Reservas ==========
                    1. Registrar reserva.
                    2. Listar reservas.
                    3. Buscar reserva por ID.
                    4. Actualizar reserva.
                    5. Eliminar reserva.
                    6. Regresar al menú principal.
                    """);
            opcion = consola.leerEntero("Elige una opción:");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void registrar() {
        System.out.println("===== Registrar Reserva =====");
        int id = consola.leerEntero("Ingrese el id de la reserva:");
        LocalDateTime fecha = consola.leerFecha("Ingrese la fecha de la reserva:").atStartOfDay();
        double monto = consola.leerEntero("Ingrese el monto total:");

        Optional<Turista> turista = buscarTurista();
        if (turista.isEmpty()) return;

        Optional<PrestadorLocal> prestador = buscarPrestador();
        if (prestador.isEmpty()) return;

        Optional<Itinerario> itinerario = buscarItinerario();
        if (itinerario.isEmpty()) return;

        Optional<Reserva> registrada = reservaService.registrarReserva(id, fecha, EstadoReserva.PENDIENTE,
                monto, turista.get(), prestador.get(), itinerario.get());

        if (registrada.isPresent()) {
            System.out.println("Reserva registrada con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe una reserva con el ID " + id);
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Reservas ======\n");
        List<Reserva> lista = reservaService.listarReservas();

        if (lista.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (Reserva r : lista) {
            mostrarReserva(r);
        }
    }

    private void buscar() {
        int id = consola.leerEntero("Ingrese el id de la reserva a buscar:");
        reservaService.buscarReservaPorId(id).ifPresentOrElse(
                this::mostrarReserva,
                () -> System.out.println("No se encontró la reserva con el ID: " + id));
    }

    private void actualizar() {
        int id = consola.leerEntero("Ingrese el id de la reserva a actualizar:");
        Optional<Reserva> existente = reservaService.buscarReservaPorId(id);
        if (existente.isEmpty()) {
            System.out.println("No existe una reserva con el ID: " + id);
            return;
        }
        mostrarReserva(existente.get());

        LocalDateTime fecha = consola.leerFecha("Ingrese la nueva fecha:").atStartOfDay();
        EstadoReserva estado = pedirEstado();
        double monto = consola.leerEntero("Ingrese el nuevo monto total:");

        Optional<Turista> turista = buscarTurista();
        if (turista.isEmpty()) return;
        Optional<PrestadorLocal> prestador = buscarPrestador();
        if (prestador.isEmpty()) return;
        Optional<Itinerario> itinerario = buscarItinerario();
        if (itinerario.isEmpty()) return;

        Optional<Reserva> actualizada = reservaService.actualizarReserva(id, fecha, estado, monto,
                turista.get(), prestador.get(), itinerario.get());

        if (actualizada.isPresent()) {
            System.out.println("Reserva actualizada con éxito");
        } else {
            System.out.println("No se pudo actualizar la reserva con el ID " + id);
        }
    }

    private void eliminar() {
        int id = consola.leerEntero("Ingrese el id de la reserva a eliminar:");
        if (reservaService.eliminarReservaPorId(id)) {
            System.out.println("Se eliminó la reserva con el ID: " + id);
        } else {
            System.out.println("No existe una reserva con el ID: " + id);
        }
    }

    // ---------- Auxiliares ----------

    private void mostrarReserva(Reserva r) {
        System.out.println(" ID: " + r.getIdReserva()
                + "\n  Fecha: " + r.getFechaReserva()
                + "\n  Estado: " + r.getEstado()
                + "\n  Monto: " + r.getMontoTotal());
    }

    private EstadoReserva pedirEstado() {
        while (true) {
            String texto = consola.leerTexto("Estado (PENDIENTE, CONFIRMADA, COMPLETADA, CANCELADA):")
                    .trim().toUpperCase();
            try {
                return EstadoReserva.valueOf(texto);
            } catch (IllegalArgumentException e) {
                System.out.println("Estado no válido.");
            }
        }
    }

    private Optional<Turista> buscarTurista() {
        int id = consola.leerEntero("Ingrese el ID del turista:");
        Optional<Turista> t = turistaService.buscarTuristaPorId(id);
        if (t.isEmpty()) System.out.println("No existe un turista con el ID " + id);
        return t;
    }

    private Optional<PrestadorLocal> buscarPrestador() {
        int id = consola.leerEntero("Ingrese el ID del prestador local:");
        Optional<PrestadorLocal> p = prestadorLocalService.buscarPrestadorPorId(id);
        if (p.isEmpty()) System.out.println("No existe un prestador local con el ID " + id);
        return p;
    }

    private Optional<Itinerario> buscarItinerario() {
        int id = consola.leerEntero("Ingrese el ID del itinerario:");
        Optional<Itinerario> i = Optional.ofNullable(itinerarioService.buscarId(id));
        if (i.isEmpty()) System.out.println("No existe un itinerario con el ID " + id);
        return i;
    }
}