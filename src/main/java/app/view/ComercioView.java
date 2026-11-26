package app.view;

import app.domain.Comercio;
import app.domain.enums.TipoComercio;
import app.service.inputport.ComercioService;

import java.util.List;
import java.util.Optional;

public class ComercioView {
    private final ComercioService comercioService;
    private final Consola consola;

    public ComercioView(ComercioService comercioService, Consola consola) {
        this.comercioService = comercioService;
        this.consola = consola;
    }

    public void mostrarMenuComercios() {
        int opcion;
        do {
            System.out.println("\n========== Menú de Comercios ==========");
            System.out.println("1. Registrar Comercio.");
            System.out.println("2. Mostrar todos los Comercios.");
            System.out.println("3. Buscar Comercio por su ID.");
            System.out.println("4. Actualizar Comercio.");
            System.out.println("5. Eliminar Comercio por su ID.");
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
        System.out.println("===== Datos del Comercio =====");
        int id = consola.leerEntero("Ingrese el id del Comercio:");
        String nombre = consola.leerTexto("Ingrese el nombre del comercio:");
        TipoComercio tipo = leerTipo();
        String direccion = consola.leerTexto("Ingrese la dirección:");
        String horario = consola.leerTexto("Ingrese el horario de atención:");
        double tarifa = leerDecimal("Ingrese la tarifa base:");
        boolean insignia = consola.leerBooleano("¿Tiene insignia especializada? (Verdadero/Falso)");
        double calificacion = leerDecimal("Ingrese la calificación promedio:");

        Optional<Comercio> registrado = comercioService.registrarComercio(id, nombre, tipo, direccion,
                horario, tarifa, insignia, calificacion);
        if (registrado.isPresent()) {
            System.out.println("Comercio registrado con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe un comercio con el ID " + id);
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Comercios ======\n");
        List<Comercio> lista = comercioService.Listarcomercios();
        if (lista.isEmpty()) {
            System.out.println("No hay comercios registrados.");
            return;
        }
        for (Comercio c : lista) {
            mostrarComercio(c);
        }
    }

    private void buscarPorId() {
        System.out.println("====== Buscar Comercio por ID ======");
        int id = consola.leerEntero("Ingrese el id del Comercio a buscar:");
        comercioService.buscarComercioPorId(id).ifPresentOrElse(
                this::mostrarComercio,
                () -> System.out.println("No se encontró un comercio con el ID: " + id));
    }

    private void actualizar() {
        System.out.println("====== Actualizar Comercio ======");
        int id = consola.leerEntero("Ingrese el id del Comercio a actualizar:");
        if (comercioService.buscarComercioPorId(id).isEmpty()) {
            System.out.println("No existe un comercio con el ID: " + id);
            return;
        }

        String nombre = consola.leerTexto("Ingrese el nuevo nombre:");
        TipoComercio tipo = leerTipo();
        String direccion = consola.leerTexto("Ingrese la nueva dirección:");
        String horario = consola.leerTexto("Ingrese el nuevo horario de atención:");
        double tarifa = leerDecimal("Ingrese la nueva tarifa base:");
        boolean insignia = consola.leerBooleano("¿Tiene insignia especializada? (Verdadero/Falso)");
        double calificacion = leerDecimal("Ingrese la nueva calificación promedio:");

        Optional<Comercio> actualizado = comercioService.actualizarComercio(id, nombre, tipo, direccion,
                horario, tarifa, insignia, calificacion);
        if (actualizado.isPresent()) {
            System.out.println("Comercio actualizado con éxito");
        } else {
            System.out.println("No se pudo actualizar el comercio con el ID " + id);
        }
    }

    private void eliminar() {
        System.out.println("====== Eliminar Comercio por ID ======");
        int id = consola.leerEntero("Ingrese el id del Comercio a eliminar:");
        if (comercioService.eliminarComercioPorId(id)) {
            System.out.println("Comercio eliminado con éxito");
        } else {
            System.out.println("No existe un comercio con el ID: " + id);
        }
    }

    private TipoComercio leerTipo() {
        while (true) {
            String texto = consola.leerTexto("Ingrese el tipo (GASTRONOMIA, ARTESANIAS, SERVICIOS u OTROS):");
            try {
                return TipoComercio.valueOf(texto.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo no válido, intenta de nuevo.");
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(consola.leerTexto(mensaje).trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }

    private void mostrarComercio(Comercio c) {
        System.out.println(" ID: " + c.getIdComercio()
                + "\n  Nombre: " + c.getNombreComercio()
                + "\n  Tipo: " + c.getTipoComercio()
                + "\n  Dirección: " + c.getDireccion()
                + "\n  Horario: " + c.getHorarioAtencion()
                + "\n  Tarifa base: " + c.getTarifaBase()
                + "\n  Insignia especializada: " + c.isInsigniaEspecializada()
                + "\n  Calificación promedio: " + c.getCalificacionPromedio());
    }
}