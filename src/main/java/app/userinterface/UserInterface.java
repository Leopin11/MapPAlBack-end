package app.userinterface;

import app.view.*;


public class UserInterface {
    private final VehiculoView vehiculoView;
    private final CertificadoView certificadoView;
    private final ReservaView reservaView;
    private final TuristaView turistaView;
    private final GuiaConductorView guiaConductorView;
    private final ItinerarioView itinerarioView;
    private final ComercioView comercioView;
    private final Consola consola;


    public UserInterface(VehiculoView vehiculoView, CertificadoView certificadoView, ReservaView reservaView, TuristaView turistaView, GuiaConductorView guiaConductorView, ItinerarioView itinerarioView, ComercioView comercioView, Consola consola) {
        this.vehiculoView = vehiculoView;
        this.certificadoView = certificadoView;
        this.reservaView = reservaView;
        this.turistaView = turistaView;
        this.guiaConductorView = guiaConductorView;
        this.itinerarioView = itinerarioView;
        this.comercioView = comercioView;
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
                    3.Turistas.
                    4.Certificados
                    5.Guias Conductores.
                    6.Itinerarios.
                    7.Comercios.
                    8.Salir.
                    
                    """) ;

            switch (opcion) {
                case 1:
                    vehiculoView.mostrarMenuVehiculos();
                    break;
                case 2:
                    reservaView.mostrarMenuReservas();
                    break;
                case 3:
                    turistaView.mostrarMenuTuristas();
                    break;
                case 4:
                    certificadoView.mostrarMenuCertificados();
                    break;
                case 5:
                    guiaConductorView.mostrarMenuGuias();
                    break;
                case 6:
                    itinerarioView.mostrarMenuItinerarios();
                    break;
                case 7:
                    comercioView.mostrarMenuComercios();
                    break;
                case 8:
                    System.out.println("Saliendo del Programa...");
                    break;
                default:
                    System.out.println("Ingresa una opcion del menu que sea valida.");
            }

        } while (opcion != 6);

    }
}
