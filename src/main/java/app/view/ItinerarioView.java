package app.view;

import app.domain.Itinerario;
import app.domain.enums.NivelEstimulacion;
import app.service.inputport.ItinerarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ItinerarioView {
    private final ItinerarioService itinerarioService;
    private final Consola consola;

    public ItinerarioView(ItinerarioService itinerarioService, Consola consola) {
        this.itinerarioService = itinerarioService;
        this.consola = consola;
    }

    public void mostrarMenuItinerarios() {
        int opcion;
        do {
            System.out.println("\n========== Menú de Itinerarios ==========");
            System.out.println("1. Registrar Itinerario.");
            System.out.println("2. Mostrar todos los Itinerarios.");
            System.out.println("3. Buscar Itinerario por su ID.");
            System.out.println("4. Actualizar Itinerario.");
            System.out.println("5. Eliminar Itinerario por su ID.");
            System.out.println("6. Regresar al Menú Principal.");

            opcion = consola.leerEntero("Elige una opción:");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> buscarPorId();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 6 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void registrar() {
        System.out.println("===== Datos del Itinerario =====");
        int id = consola.leerEntero("Ingrese el id del Itinerario:");
        Itinerario itinerario = leerDatos(id);

        try {
            Optional<Itinerario> registrado = itinerarioService.registrarItinerario(itinerario);
            if (registrado.isPresent()) {
                System.out.println("Itinerario registrado con éxito");
            } else {
                System.out.println("No se pudo registrar: ya existe un itinerario con el ID " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Itinerarios ======\n");
        List<Itinerario> lista = itinerarioService.listarItinerarios();
        if (lista.isEmpty()) {
            System.out.println("No hay itinerarios registrados.");
            return;
        }
        for (Itinerario i : lista) {
            mostrarItinerario(i);
        }
    }

    private void buscarPorId() {
        System.out.println("====== Buscar Itinerario por ID ======");
        int id = consola.leerEntero("Ingrese el id del Itinerario a buscar:");
        itinerarioService.buscarItinerarioPorId(id).ifPresentOrElse(
                this::mostrarItinerario,
                () -> System.out.println("No se encontró un itinerario con el ID: " + id));
    }

    private void actualizar() {
        System.out.println("====== Actualizar Itinerario ======");
        int id = consola.leerEntero("Ingrese el id del Itinerario a actualizar:");
        Itinerario itinerario = leerDatos(id);

        try {
            Optional<Itinerario> actualizado = itinerarioService.actualizarItinerario(itinerario);
            if (actualizado.isPresent()) {
                System.out.println("Itinerario actualizado con éxito");
            } else {
                System.out.println("No existe un itinerario con el ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        System.out.println("====== Eliminar Itinerario por ID ======");
        int id = consola.leerEntero("Ingrese el id del Itinerario a eliminar:");
        if (itinerarioService.eliminarItinerarioPorId(id)) {
            System.out.println("Itinerario eliminado con éxito");
        } else {
            System.out.println("No existe un itinerario con el ID: " + id);
        }
    }

    private Itinerario leerDatos(int id) {
        String destino = consola.leerTexto("Ingrese el destino:");
        LocalDate fechaInicio = consola.leerFecha("Ingrese la fecha de inicio:");
        LocalDate fechaFin = consola.leerFecha("Ingrese la fecha de fin:");
        String tipoExperiencia = consola.leerTexto("Ingrese el tipo de experiencia:");
        NivelEstimulacion nivel = leerNivel();
        return new Itinerario(id, destino, fechaInicio, fechaFin, tipoExperiencia, nivel);
    }

    private NivelEstimulacion leerNivel() {
        while (true) {
            String texto = consola.leerTexto("Ingrese el nivel de estimulación (BAJA, MEDIA o ALTA):");
            try {
                return NivelEstimulacion.valueOf(texto.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Nivel no válido, intenta de nuevo.");
            }
        }
    }

    private void mostrarItinerario(Itinerario i) {
        System.out.println(" ID: " + i.getIdItinerario()
                + "\n  Destino: " + i.getDestino()
                + "\n  Fecha de inicio: " + i.getFechaDeInicio()
                + "\n  Fecha de fin: " + i.getFechaFin()
                + "\n  Tipo de experiencia: " + i.getTipoExperiencia()
                + "\n  Nivel de estimulación: " + i.getNivelEstimulacion());
    }
}