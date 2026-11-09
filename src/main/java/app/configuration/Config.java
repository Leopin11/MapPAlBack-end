package app.configuration;

import app.domain.Vehiculo;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mapper.RowMapper;
import app.persistence.mapper.VehiculoMapper;
import app.persistence.repository.CertificadoRepositoryImpl;
import app.persistence.repository.VehiculoRepositoryAdapterDB;
import app.persistence.repository.VehiculoRepositoryImpl;
import app.service.CertificadoServiceImpl;
import app.service.VehiculoServiceImpl;
import app.service.inputport.CertificadoService;
import app.service.inputport.VehiculoService;
import app.service.outputport.CertificadoRepository;
import app.service.outputport.VehiculoRepository;
import app.userinterface.UserInterface;
import app.view.CertificadoView;
import app.view.VehiculoView;
import app.view.Consola;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Config {

    public UserInterface construirUserInterface(Scanner scanner) throws SQLException {
        Connection coneccion = DataBaseConnection.getInstance().getConnection();
        RowMapper<Vehiculo> rowMapper = new VehiculoMapper();
        Consola consola = new Consola(scanner);


        VehiculoRepository vehiculoRepository = new VehiculoRepositoryAdapterDB(coneccion, (VehiculoMapper) rowMapper);
        VehiculoService vehiculoService = new VehiculoServiceImpl(vehiculoRepository);
        VehiculoView vehiculoView = new VehiculoView(vehiculoService, consola);

        CertificadoRepository certificadoRepository = new CertificadoRepositoryImpl();
        CertificadoService certificadoService = new CertificadoServiceImpl(certificadoRepository);
        CertificadoView certificadoView = new CertificadoView(certificadoService, consola);


        return new UserInterface(vehiculoView, certificadoView, consola);
    }
}
