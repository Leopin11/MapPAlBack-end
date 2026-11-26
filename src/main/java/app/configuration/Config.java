package app.configuration;

import app.domain.*;
import app.domain.relations.VehiculoEtiqueta;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mapper.*;
import app.persistence.repository.*;
import app.service.*;
import app.service.inputport.*;
import app.service.outputport.*;
import app.userinterface.UserInterface;
import app.view.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Config {

    public UserInterface construirUserInterface(Scanner scanner) throws SQLException {
        Connection coneccion = DataBaseConnection.getInstance().getConnection();
        RowMapper<Vehiculo> rowMapperV = new VehiculoMapper();
        RowMapper<VehiculoEtiqueta> rowMapperVE = new VehiculoEtiquetaMapper();
        ItinerarioMapper itinerarioMapper = new ItinerarioMapper();
        Consola consola = new Consola(scanner);


        VehiculoRepository vehiculoRepository = new VehiculoRepositoryAdapterDB(coneccion, (VehiculoMapper) rowMapperV, (VehiculoEtiquetaMapper) rowMapperVE);
        VehiculoService vehiculoService = new VehiculoServiceImpl(vehiculoRepository);
        VehiculoView vehiculoView = new VehiculoView(vehiculoService, consola);

        CertificadoRepository certificadoRepository = new CertificadoRepositoryImpl();
        CertificadoService certificadoService = new CertificadoServiceImpl(certificadoRepository);
        CertificadoView certificadoView = new CertificadoView(certificadoService, consola);

        // Turista
        TuristaMapper turistaMapper = new TuristaMapper();
        TuristaRepository turistaRepository = new TuristaRepositorioAdapterDB(coneccion, turistaMapper);
        TuristaService turistaService = new TuristaServiveImpl(turistaRepository);
        TuristaView turistaView = new TuristaView(turistaService, consola);

// Itinerario
        ItinerarioRepository itinerarioRepository = new ItinerarioRepositoryAdapterDB(coneccion, new ItinerarioMapper());
        ItinerarioService itinerarioService = new ItinerarioServiceImpl(itinerarioRepository);
        ItinerarioView itinerarioView = new ItinerarioView(itinerarioService, consola);

// GuiaConductor
        GuiaConductorRepository guiaConductorRepository = new GuiaConductorRepositoryAdapterDB(coneccion, new GuiaConductorMapper());
        GuiaConductorService guiaConductorService = new GuiaConductorServicioImpl(guiaConductorRepository);
        GuiaConductorView guiaConductorView = new GuiaConductorView(guiaConductorService,consola);

        // Comercio
        ComercioMapper comercioMapper = new ComercioMapper();
        ComercioRepository comercioRepository = new ComercioRepositoryAdapterDB(coneccion, comercioMapper);
        ComercioService comercioService = new ComercioServicioImpl(comercioRepository);
        ComercioView comercioView = new ComercioView(comercioService, consola);


        // Reserva
        ReservaMapper reservaMapper = new ReservaMapper();
        ReservaRepository reservaRepository = new ReservaRepositoryAdapterDB(coneccion, reservaMapper,
                turistaMapper, itinerarioMapper, guiaConductorRepository, comercioRepository);
        ReservaService reservaService = new ReservaServiceImpl(reservaRepository);
        ReservaView reservaView = new ReservaView(reservaService, turistaService, itinerarioService, guiaConductorService, consola);

        return new UserInterface(vehiculoView, certificadoView, reservaView, turistaView, guiaConductorView, itinerarioView,comercioView,consola);
    }
}
