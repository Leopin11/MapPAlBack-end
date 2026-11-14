package app.configuration;

import app.domain.Vehiculo;
import app.domain.relations.VehiculoEtiqueta;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mapper.ReservaMapper;
import app.persistence.mapper.RowMapper;
import app.persistence.mapper.VehiculoEtiquetaMapper;
import app.persistence.mapper.VehiculoMapper;
import app.persistence.repository.*;
import app.service.*;
import app.service.inputport.*;
import app.service.outputport.*;
import app.userinterface.UserInterface;
import app.view.CertificadoView;
import app.view.ReservaView;
import app.view.VehiculoView;
import app.view.Consola;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Config {

    public UserInterface construirUserInterface(Scanner scanner) throws SQLException {
        Connection coneccion = DataBaseConnection.getInstance().getConnection();
        RowMapper<Vehiculo> rowMapperV = new VehiculoMapper();
        RowMapper<VehiculoEtiqueta> rowMapperVE = new VehiculoEtiquetaMapper();
        Consola consola = new Consola(scanner);


        VehiculoRepository vehiculoRepository = new VehiculoRepositoryAdapterDB(coneccion, (VehiculoMapper) rowMapperV, (VehiculoEtiquetaMapper) rowMapperVE);
        VehiculoService vehiculoService = new VehiculoServiceImpl(vehiculoRepository);
        VehiculoView vehiculoView = new VehiculoView(vehiculoService, consola);

        CertificadoRepository certificadoRepository = new CertificadoRepositoryImpl();
        CertificadoService certificadoService = new CertificadoServiceImpl(certificadoRepository);
        CertificadoView certificadoView = new CertificadoView(certificadoService, consola);

        TuristaRepository turistaRepository = new TuristaRepositoryImpl(DatosSemilla.turistas());
        TuristaService turistaService = new TuristaServiveImpl(turistaRepository);

        PrestadorLocalRepository prestadorLocalRepository =
                new PrestadorLocalRepositoryImpl(new ArrayList<>(DatosSemilla.prestadores()));
        PrestadorLocalService prestadorLocalService = new PrestadorLocalServiceImpl(prestadorLocalRepository);

        ItinerarioRepository itinerarioRepository = new ItinerarioRepositoryTemporal();
        DatosSemilla.itinerarios().forEach(itinerarioRepository::guardarItinerario);
        ItinerarioService itinerarioService = new ItinerarioServiceImpl(itinerarioRepository);

        // ==================== RESERVA ====================
        ReservaRepository reservaRepository = new ReservaRepositoryAdapterDB(
                coneccion, new ReservaMapper(), turistaRepository, prestadorLocalRepository, itinerarioRepository);
        ReservaService reservaService = new ReservaServiceImpl(reservaRepository);
        ReservaView reservaView = new ReservaView(
                reservaService, turistaService, prestadorLocalService, itinerarioService, consola);


        return new UserInterface(vehiculoView, certificadoView, reservaView, consola);
    }
}
