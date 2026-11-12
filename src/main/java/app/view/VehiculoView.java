package app.view;

import app.domain.Vehiculo;
import app.domain.relations.VehiculoEtiqueta;
import app.service.inputport.VehiculoService;

import java.util.List;
import java.util.Optional;

public class VehiculoView {
    private final VehiculoService vehiculoService;
    private final Consola consola;

    public VehiculoView(VehiculoService vehiculoService, Consola consola) {
        this.vehiculoService = vehiculoService;
        this.consola = consola;
    }

    private void guardar(){
        System.out.println("===== Datos del Vehículo =====");

        int idVehiculo = consola.leerEntero("Ingrese el id del Vehículo:");
        String placa = consola.leerTexto("Ingrese la placa del Vehículo:");
        String marca = consola.leerTexto("Ingrese la marca del Vehículo:");
        int capacidadPasajeros = consola.leerEntero("Ingrese la capacidad de pasajeros del Vehículo:");
        boolean baulCapacidadSilla = consola.leerBooleano("¿Tiene capacidad para silla en el baúl?");

        Optional<Vehiculo> registrado = vehiculoService.registrarVehiculo(idVehiculo, placa, marca, capacidadPasajeros, baulCapacidadSilla);

        if (registrado.isPresent()) {
            System.out.println("Vehículo registrado con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe un vehículo con el ID " + idVehiculo);
        }
    }

    private void listar() {
        System.out.println("\n====== Lista de Vehículos ==========\n");
        List<Vehiculo> lista = vehiculoService.listarVehiculos();

        if (lista.isEmpty()){
            System.out.println("No hay Vehículos Registrados!");
        } else {
            for (Vehiculo vehiculo : lista) {
                System.out.println(" ID: " + vehiculo.getIdVehiculo() +
                        "\n  Placa: " + vehiculo.getPlaca() +
                        "\n  Marca: " + vehiculo.getMarca() +
                        "\n  Capacidad de Pasajeros: " + vehiculo.getCapacidadPasajeros() +
                        "\n  Baúl Grande: " + vehiculo.isBaulCapacidadSilla()
                );
            }
        }
    }

    private void buscarId() {
        System.out.println("====== Buscar Vehículo por ID =====");
        int id = consola.leerEntero("Ingrese el id del Vehículo que desea Buscar:");

        Optional<Vehiculo> encontrado = vehiculoService.buscarVehiculoPorId(id);
        if(encontrado.isPresent()) {
            System.out.println("Se encontró el Vehículo con ID: " + encontrado.get().getIdVehiculo()
                    +       "\n  Placa: " + encontrado.get().getPlaca() +
                            "\n  Marca: " + encontrado.get().getMarca() +
                            "\n  Capacidad de Pasajeros: " + encontrado.get().getCapacidadPasajeros() +
                            "\n  Baúl Grande: " + encontrado.get().isBaulCapacidadSilla());
        } else {
            System.out.println("No se encontró el Vehículo con el ID: " + id);
        }
    }

    private void actualizar() {
        System.out.println("========= Actualizar Vehículo =====");
        int id = consola.leerEntero("Digite el id del vehículo a actualizar:");

        Optional<Vehiculo> existente = vehiculoService.buscarVehiculoPorId(id);
        if(existente.isEmpty()) {
            System.out.println("No existe un vehículo con el ID: " + id);
            return;
        }

        Vehiculo vehiculoActual = existente.get();
        System.out.println("Datos actuales -> Placa: " + vehiculoActual.getPlaca()
                + " | Marca: " + vehiculoActual.getMarca()
                + " | Capacidad: " + vehiculoActual.getCapacidadPasajeros() + " | Baúl silla: " + vehiculoActual.isBaulCapacidadSilla());

        String placa = consola.leerTexto("Ingrese la nueva placa del vehículo:");
        String marca = consola.leerTexto("Ingrese la nueva marca del vehículo:");
        int capacidadPasajeros = consola.leerEntero("Ingrese la nueva capacidad de pasajeros:");
        boolean baulCapacidadSilla = consola.leerBooleano("¿Tiene capacidad para silla en el baúl?");

        Optional<Vehiculo> vehiculoActualizado = vehiculoService.actualizarVehiculo(id, placa, marca, capacidadPasajeros, baulCapacidadSilla);

        if(vehiculoActualizado.isPresent()) {
            System.out.println("Vehículo actualizado con éxito");
        } else {
            System.out.println("No se pudo actualizar: no existe un vehículo con el ID " + id);
        }
    }

    private void eliminar() {
        System.out.println("===== Eliminar Vehículo ======");
        int id = consola.leerEntero("Digita el id del vehículo a eliminar:");

        if (vehiculoService.eliminarVehiculoPorId(id)) {
            System.out.println("Se eliminó el vehículo con el ID: " + id);
        } else {
            System.out.println("No existe un vehículo con el ID: " + id);
        }
    }

    private void mostrarEtiquetasDeVehiculo() {
        System.out.println("==== Mostrar Etiquetas del Vehiculo =====");

        int id = consola.leerEntero("Digita el id del Vehiculo");
        List<VehiculoEtiqueta> etiquetas = vehiculoService.obtenerEtiquetasDeVehiculo(id);

        if (etiquetas.isEmpty()) {
            System.out.println("Vehiculo no tiene etiqueta asignada");
        } else {
            for(VehiculoEtiqueta vehiculoEtiqueta : etiquetas) {
                System.out.println("-> Nombre de la etiqueta: " + vehiculoEtiqueta.getNombreEtiqueta()
                        + "\n->  Categoria de la etiqueta: " + vehiculoEtiqueta.getCategoria());
            }
        }
    }

    public void mostrarMenuVehiculos() {
        int opcion = 0;
        do {
            System.out.println("""
                    ========== ¿Que vas a Realizar? =========
                    1. Registrar Vehículo.
                    2. Mostrar todos los Vehículos.
                    3. Buscar un Vehículo por su ID.
                    4. Actualizar.
                    5. Eliminar Vehículo por su ID.
                    6. Motrar etiquetas de un Vehículo por su ID.
                    7. Regresar al Menu Principal.
                    """);

            opcion = consola.leerEntero("Elige una opción:");

            switch (opcion) {
                case 1:
                    guardar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscarId();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 6:
                    mostrarEtiquetasDeVehiculo();
                    break;
                case 7:
                    System.out.println("Regresar al menu principal");
                    break;
                default:
                    System.out.println("Ingresa una opcion del menu que sea valida.");
            }

        } while (opcion != 7);

    }
}
