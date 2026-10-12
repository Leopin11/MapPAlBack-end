package view;

import domain.Vehiculo;
import service.VehiculoService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehiculoView {
    private final VehiculoService vehiculoService;
    private final Scanner sc;

    public VehiculoView(VehiculoService vehiculoService, Scanner sc) {
        this.vehiculoService = vehiculoService;
        this.sc = sc;
    }

    private  void guardarVehiculo(){
        try {

            System.out.println("=====Datos del Vehiculo=====");

            System.out.println("ingrese el id del Vehiculo:");
            int idVehiculo = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese la placa del Vehiculo:");
            String placa = sc.nextLine();

            System.out.println("Ingrese la marca del Vehiculo:");
            String marca = sc.nextLine();

            System.out.println("Ingrese capasidad de pasajeros del Vehiculo:");
            int capacidadPasajeros = sc.nextInt();
            sc.nextLine();

            System.out.println("¿Tiene capacidad para silla en el baúl? (Verdadero/Falso)");
            boolean baulCapacidadSilla = sc.nextBoolean();


            vehiculoService.registrarVehiculo(idVehiculo,placa,marca,capacidadPasajeros,baulCapacidadSilla);

        } catch (InputMismatchException e) {
            System.out.println("Se requieren valores numericos validos para el id y capacidad de pasajeros" + e.getMessage());

        }
    }

    private void listarVehiculos() {
        System.out.println("\n====== Lista de Vehículos ==========\n");
        List<Vehiculo> lista = vehiculoService.mostrarVehiculos();

        if (lista.isEmpty()){
            System.out.println("No hay Vehiculos Registrados!");
        } else {
            for (Vehiculo vehiculo : lista) {
                System.out.println(" ID: " + vehiculo.getIdVehiculo() +
                        "\n  Placa: " + vehiculo.getPlaca() +
                        "\n  Marca: " + vehiculo.getMarca() +
                        "\n  Capacidad de Pasageros: " + vehiculo.getCapacidadPasajeros() +
                        "\n  Baul Grande: " + vehiculo.isBaulCapacidadSilla()
                );
            }
        }
    }

    private void buscarVehiculoPorId() {
        System.out.println("""
                ====== Buscar Vehículo por ID =====
                Ingrese el id del Vehículo que desea Buscar:
                """);
        int id = sc.nextInt();
        sc.nextLine();

        Vehiculo idDeVehiculoABuscar = vehiculoService.buscarId(id);
        if(idDeVehiculoABuscar != null){
            System.out.println("Se encontro el Vehículo con ID: " + idDeVehiculoABuscar.getIdVehiculo());
        } else {
            System.out.println("No se encontro Vehiculo con el ID: " + id);
        }
    }

    private void eliminarVehiculoPorId() {
        System.out.println("""
                ===== Eliminar Vehículo =======
                Digita el id del vehículo a eliminar:
                """);
        int idDeVehiculoAEliminar = sc.nextInt();
        sc.nextLine();

        vehiculoService.eliminar(idDeVehiculoAEliminar);
        System.out.println("Se elimino el vehiculo con el ID: " + idDeVehiculoAEliminar);

    }

    public void mostrarMenu() {
        int option = 0;
        do {
            System.out.println("""
                    ========== ¿QUE VAS A REALIZAR? =========
                    1. Registrar Vehículo.
                    2. Mostrar todos los Vehículos.
                    3. Buscar un Vehículo por su ID.
                    4. Eliminar Vehículo por su ID.
                    5. Salir.
                    """);

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    guardarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    buscarVehiculoPorId();
                    break;
                case 4:
                    eliminarVehiculoPorId();
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Ingresa una opcion del menu que sea valida.");
            }

        } while (option != 5);

    }
}
