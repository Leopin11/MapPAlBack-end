package app.configuration;

import app.repository.VehiculoRepositoryImpl;
import app.service.VehiculoServiceImpl;
import app.service.inputport.VehiculoService;
import app.service.outputport.VehiculoRepository;
import app.userinterface.UserInterface;
import app.view.VehiculoView;
import app.view.Consola;

import java.util.Scanner;

public class Config {

    public UserInterface construirUserInterface(Scanner scanner) {
        Consola consola = new Consola(scanner);

        VehiculoRepository vehiculoRepository = new VehiculoRepositoryImpl();
        VehiculoService vehiculoService = new VehiculoServiceImpl(vehiculoRepository);
        VehiculoView vehiculoView = new VehiculoView(vehiculoService, consola);

        return new UserInterface(vehiculoView, consola);
    }
}
