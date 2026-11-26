package app.view;

import app.domain.GuiaConductor;
import app.service.inputport.GuiaConductorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GuiaConductorView {
    private final GuiaConductorService guiaConductorService;
    private final Consola consola;

    public GuiaConductorView(GuiaConductorService guiaConductorService, Consola consola) {
        this.guiaConductorService = guiaConductorService;
        this.consola = consola;
    }

    public void mostrarMenuGuias() {
        int opcion;
        do {
            System.out.println("\n========== Menú de Guías de Conductor ==========");
            System.out.println("1. Registrar Guía.");
            System.out.println("2. Mostrar todos los Guías.");
            System.out.println("3. Buscar Guía por su ID.");
            System.out.println("4. Actualizar tarifa base.");
            System.out.println("5. Eliminar Guía por su ID.");
            System.out.println("6. Regresar al Menú Principal.");

            opcion = consola.leerEntero("Elige una opción:");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> buscarPorId();
                case 4 -> actualizarTarifa();
                case 5 -> eliminar();
                case 6 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void registrar() {
        System.out.println("===== Datos del Guía =====");
        int id = consola.leerEntero("Ingrese el id del Guía:");
        String nombre = consola.leerTexto("Ingrese el nombre:");
        String apellido = consola.leerTexto("Ingrese el apellido:");
        String documento = consola.leerTexto("Ingrese el documento:");
        String telefono = consola.leerTexto("Ingrese el teléfono:");
        String email = consola.leerTexto("Ingrese el email:");
        LocalDate fechaNacimiento = consola.leerFecha("Ingrese la fecha de nacimiento:");
        boolean activo = consola.leerBooleano("¿Está activo? (Verdadero/Falso)");
        double tarifaBase = consola.leerDouble("Ingrese la tarifa base:");
        boolean insignia = consola.leerBooleano("¿Tiene insignia especializada? (Verdadero/Falso)");
        double calificacion = consola.leerDouble("Ingrese la calificación promedio:");

        GuiaConductor guia = new GuiaConductor(id, nombre, apellido, documento, telefono, email,
                fechaNacimiento, activo, tarifaBase, insignia, calificacion);

        Optional<GuiaConductor> registrado = guiaConductorService.registrarGuiaConductor(guia, id);
        if (registrado.isPresent()) {
            System.out.println("Guía registrado con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe un guía con el ID " + id);
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Guías ======\n");
        List<GuiaConductor> lista = guiaConductorService.listarGuiasConductores();
        if (lista.isEmpty()) {
            System.out.println("No hay guías registrados.");
            return;
        }
        for (GuiaConductor g : lista) {
            mostrarGuia(g);
        }
    }

    private void buscarPorId() {
        System.out.println("====== Buscar Guía por ID ======");
        int id = consola.leerEntero("Ingrese el id del Guía a buscar:");
        guiaConductorService.buscarGuiaConductor(id).ifPresentOrElse(
                this::mostrarGuia,
                () -> System.out.println("No se encontró un guía con el ID: " + id));
    }

    private void actualizarTarifa() {
        System.out.println("====== Actualizar tarifa base ======");
        int id = consola.leerEntero("Ingrese el id del Guía:");
        double nuevaTarifa = consola.leerDouble("Ingrese la nueva tarifa base:");

        Optional<GuiaConductor> actualizado = guiaConductorService.actualizarTarifaBase(id, nuevaTarifa);
        if (actualizado.isPresent()) {
            System.out.println("Tarifa actualizada con éxito");
        } else {
            System.out.println("No existe un guía con el ID: " + id);
        }
    }

    private void eliminar() {
        System.out.println("====== Eliminar Guía por ID ======");
        int id = consola.leerEntero("Ingrese el id del Guía a eliminar:");
        if (guiaConductorService.eliminarGuiaConductor(id)) {
            System.out.println("Guía eliminado con éxito");
        } else {
            System.out.println("No existe un guía con el ID: " + id);
        }
    }

    private void mostrarGuia(GuiaConductor g) {
        System.out.println(" ID: " + g.getIdPersona()
                + "\n  Nombre: " + g.getNombre() + " " + g.getApellido()
                + "\n  Documento: " + g.getDocumentoId()
                + "\n  Teléfono: " + g.getTelefono()
                + "\n  Email: " + g.getEmail()
                + "\n  Fecha de nacimiento: " + g.getFechaNacimiento()
                + "\n  Activo: " + g.isActivo()
                + "\n  Tarifa base: " + g.getTarifaBase()
                + "\n  Insignia especializada: " + g.isInsigniaEspecializada()
                + "\n  Calificación promedio: " + g.getCalificacionPromedio());
    }
}