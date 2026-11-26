package app.view;

import app.domain.Turista;
import app.service.inputport.TuristaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TuristaView {
    private final TuristaService turistaService;
    private final Consola consola;

    public TuristaView(TuristaService turistaService, Consola consola) {
        this.turistaService = turistaService;
        this.consola = consola;
    }

    public void mostrarMenuTuristas() {
        int opcion;
        do {
            System.out.println("\n========== Menú de Turistas ==========");
            System.out.println("1. Registrar Turista.");
            System.out.println("2. Mostrar todos los Turistas.");
            System.out.println("3. Buscar Turista por su ID.");
            System.out.println("4. Actualizar Turista.");
            System.out.println("5. Eliminar Turista por su ID.");
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
        System.out.println("===== Datos del Turista =====");

        int idPersona = consola.leerEntero("Ingrese el id del Turista:");
        String nombre = consola.leerTexto("Ingrese el nombre:");
        String apellido = consola.leerTexto("Ingrese el apellido:");
        String documento = consola.leerTexto("Ingrese el documento:");
        String telefono = consola.leerTexto("Ingrese el teléfono:");
        String email = consola.leerTexto("Ingrese el email:");
        LocalDate fechaNacimiento = consola.leerFecha("Ingrese la fecha de nacimiento:");
        boolean activo = consola.leerBooleano("¿Está activo? (Verdadero/Falso)");
        String nacionalidad = consola.leerTexto("Ingrese la nacionalidad:");
        String idiomaPreferido = consola.leerTexto("Ingrese el idioma preferido:");

        Optional<Turista> registrado = turistaService.registrarTurista(idPersona, nombre, apellido, documento,
                telefono, email, fechaNacimiento, activo, nacionalidad, idiomaPreferido);

        if (registrado.isPresent()) {
            System.out.println("Turista registrado con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe un turista con el ID " + idPersona);
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Turistas ======\n");
        List<Turista> lista = turistaService.listarTuristas();

        if (lista.isEmpty()) {
            System.out.println("No hay turistas registrados.");
            return;
        }
        for (Turista t : lista) {
            mostrarTurista(t);
        }
    }

    private void buscarPorId() {
        System.out.println("====== Buscar Turista por ID ======");
        int id = consola.leerEntero("Ingrese el id del Turista a buscar:");

        turistaService.buscarTuristaPorId(id).ifPresentOrElse(
                this::mostrarTurista,
                () -> System.out.println("No se encontró un turista con el ID: " + id));
    }

    private void actualizar() {
        System.out.println("====== Actualizar Turista ======");
        int id = consola.leerEntero("Ingrese el id del Turista a actualizar:");

        if (turistaService.buscarTuristaPorId(id).isEmpty()) {
            System.out.println("No existe un turista con el ID: " + id);
            return;
        }

        String nombre = consola.leerTexto("Ingrese el nuevo nombre:");
        String apellido = consola.leerTexto("Ingrese el nuevo apellido:");
        String documento = consola.leerTexto("Ingrese el nuevo documento:");
        String telefono = consola.leerTexto("Ingrese el nuevo teléfono:");
        String email = consola.leerTexto("Ingrese el nuevo email:");
        LocalDate fechaNacimiento = consola.leerFecha("Ingrese la nueva fecha de nacimiento:");
        boolean activo = consola.leerBooleano("¿Está activo? (Verdadero/Falso)");
        String nacionalidad = consola.leerTexto("Ingrese la nueva nacionalidad:");
        String idiomaPreferido = consola.leerTexto("Ingrese el nuevo idioma preferido:");

        Optional<Turista> actualizado = turistaService.actualizarTurista(id, nombre, apellido, documento,
                telefono, email, fechaNacimiento, activo, nacionalidad, idiomaPreferido);

        if (actualizado.isPresent()) {
            System.out.println("Turista actualizado con éxito");
        } else {
            System.out.println("No se pudo actualizar el turista con el ID " + id);
        }
    }

    private void eliminar() {
        System.out.println("====== Eliminar Turista por ID ======");
        int id = consola.leerEntero("Ingrese el id del Turista a eliminar:");

        if (turistaService.eliminarTuristaPorId(id)) {
            System.out.println("Turista eliminado con éxito");
        } else {
            System.out.println("No existe un turista con el ID: " + id);
        }
    }

    private void mostrarTurista(Turista t) {
        System.out.println(" ID: " + t.getIdPersona()
                + "\n  Nombre: " + t.getNombre() + " " + t.getApellido()
                + "\n  Documento: " + t.getDocumentoId()
                + "\n  Teléfono: " + t.getTelefono()
                + "\n  Email: " + t.getEmail()
                + "\n  Fecha de nacimiento: " + t.getFechaNacimiento()
                + "\n  Activo: " + t.isActivo()
                + "\n  Nacionalidad: " + t.getNacionalidad()
                + "\n  Idioma preferido: " + t.getIdiomaPreferido());
    }
}