package app.userinterface;

import app.persistence.dbconnection.DataBaseConnection;
import app.view.CertificadoView;
import app.view.Consola;
import app.view.ReservaView;
import app.view.VehiculoView;


public class UserInterface {
    private final VehiculoView vehiculoView;
    private final CertificadoView certificadoView;
    private final ReservaView reservaView;
    private final Consola consola;


    public UserInterface(VehiculoView vehiculoView, CertificadoView certificadoView, ReservaView reservaView, Consola consola) {
        this.vehiculoView = vehiculoView;
        this.certificadoView = certificadoView;
        this.reservaView = reservaView;
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
                    2.Reservas.
                    4.Certificados
                    6.Salir.
                    
                    """) ;

            switch (opcion) {
                case 1:
                    vehiculoView.mostrarMenuVehiculos();
                    break;
                case 2:
                    reservaView.mostrarMenuReservas();
                    break;
                case 3:
                    // A-Itinerarios
                    break;
                case 4:
                    certificadoView.mostrarMenuCertificados();
                    break;
                case 5:
                    // Etiqueta Inclusiva
                    break;
                case 6:
                    System.out.println("Saliendo del Programa...");
                    break;
                default:
                    System.out.println("Ingresa una opcion del menu que sea valida.");
            }

        } while (opcion != 6);

    }
}
