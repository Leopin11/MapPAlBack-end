package app.userinterface;

import app.view.Consola;
import app.view.VehiculoView;


public class UserInterface {
    private final VehiculoView vehiculoView;
    private final Consola consola;


    public UserInterface(VehiculoView vehiculoView, Consola consola) {
        this.vehiculoView = vehiculoView;
        this.consola = consola;
    }

    public void iniciar() {
        int opcion = 0;

        System.out.println("""
                ============================================
                            Bienvenido a MapPAl
                ============================================
                """);
        do {
            opcion = consola.leerEntero("""
                    ----- Selecciona Una Opcion ------
                    1.Vehiculos.
                    2.Salir.
                    """) ;

            switch (opcion) {
                case 1:
                    vehiculoView.mostrarMenuVehiculos();
                    break;
                case 2:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Ingresa una opcion del menu que sea valida.");
            }

        } while (opcion != 2);
    }
}
