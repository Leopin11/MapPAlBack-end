package app;

import app.repository.VehiculoRepository;
import app.service.VehiculoService;
import app.view.VehiculoView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        VehiculoRepository vehiculoRepository = new VehiculoRepository();

        VehiculoService vehiculoService = new VehiculoService(vehiculoRepository);

        VehiculoView vehiculoView = new VehiculoView(vehiculoService, scanner);

        System.out.println("""
                ============================================
                            Bienvenido a MapPAl
                ============================================
                """);

        vehiculoView.mostrarMenu();
        scanner.close();
    }

}
