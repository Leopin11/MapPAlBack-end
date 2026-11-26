package app.persistence.repository;

import app.domain.Reserva;
import app.domain.PrestadorLocal;
import app.persistence.mapper.ItinerarioMapper;
import app.persistence.mapper.ReservaMapper;
import app.persistence.mapper.TuristaMapper;
import app.service.outputport.ComercioRepository;
import app.service.outputport.GuiaConductorRepository;
import app.service.outputport.ReservaRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryAdapterDB implements ReservaRepository {

    private static final String SELECT_CON_JOIN =
            "SELECT r.IdReserva, r.Fecha_reserva, r.Estado_reserva, r.Monto_total, "
                    + "p.idPrestador, p.tipoPrestador, "
                    + "t.idPersona, t.nombre, t.apellido, t.documentoId, t.telefono, t.email, "
                    + "t.fechaNacimiento, t.activo, t.nacionalidad, t.idiomaPreferido, "
                    + "i.idItinerario, i.destino, i.fechaDeInicio, i.fechaFin, i.tipoExperiencia, i.nivelEstimulacion "
                    + "FROM Reserva r "
                    + "INNER JOIN PrestadorLocal p ON r.Prestador_local = p.idPrestador "
                    + "INNER JOIN Turista t ON r.Turista = t.idPersona "
                    + "INNER JOIN Itinerario i ON r.Itinerario = i.idItinerario";

    private final Connection conexion;
    private final ReservaMapper reservaMapper;
    private final TuristaMapper turistaMapper;
    private final ItinerarioMapper itinerarioMapper;
    private final GuiaConductorRepository guiaConductorRepository;
    private final ComercioRepository comercioRepository;

    public ReservaRepositoryAdapterDB(Connection conexion, ReservaMapper reservaMapper,
                                      TuristaMapper turistaMapper, ItinerarioMapper itinerarioMapper,
                                      GuiaConductorRepository guiaConductorRepository,
                                      ComercioRepository comercioRepository) {
        this.conexion = conexion;
        this.reservaMapper = reservaMapper;
        this.turistaMapper = turistaMapper;
        this.itinerarioMapper = itinerarioMapper;
        this.guiaConductorRepository = guiaConductorRepository;
        this.comercioRepository = comercioRepository;
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (IdReserva, Fecha_reserva, Estado_reserva, Monto_total, "
                + "Turista, Prestador_local, Itinerario) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, reserva.getIdReserva());
            setCamposComunes(ps, reserva, 2);
            ps.executeUpdate();
            return reserva;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la reserva: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Reserva> ReservasRegistrados() {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(SELECT_CON_JOIN);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                reservas.add(mapearReserva(rs));
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(int idReserva) {
        String sql = SELECT_CON_JOIN + " WHERE r.IdReserva = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearReserva(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la reserva: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Reserva> actualizarReserva(int idReserva, Reserva reserva) {
        String sql = "UPDATE Reserva SET Fecha_reserva = ?, Estado_reserva = ?, Monto_total = ?, "
                + "Turista = ?, Prestador_local = ?, Itinerario = ? WHERE IdReserva = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            setCamposComunes(ps, reserva, 1);
            ps.setInt(7, idReserva);
            return ps.executeUpdate() > 0 ? Optional.of(reserva) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la reserva: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean eliminarReservaPorId(int idReserva) {
        String sql = "DELETE FROM Reserva WHERE IdReserva = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage(), e);
        }
    }

    // Convierte una fila del JOIN en una Reserva completa
    private Reserva mapearReserva(ResultSet rs) throws SQLException {
        Reserva reserva = reservaMapper.mapRow(rs);
        reserva.setTurista(turistaMapper.mapRow(rs));
        reserva.setItinerario(itinerarioMapper.mapRow(rs));
        reserva.setPrestadorLocal(resolverPrestador(rs.getInt("idPrestador"), rs.getString("tipoPrestador")));
        return reserva;
    }

    // El tipo decide a qué repositorio pedir el prestador
    private PrestadorLocal resolverPrestador(int idPrestador, String tipo) {
        if ("GUIA".equals(tipo)) {
            return guiaConductorRepository.buscarPorId(idPrestador).orElse(null);
        }
        return comercioRepository.buscarComercioPorId(idPrestador).orElse(null);
    }

    private void setCamposComunes(PreparedStatement ps, Reserva r, int desde) throws SQLException {
        ps.setDate(desde, Date.valueOf(r.getFechaReserva().toLocalDate()));
        ps.setString(desde + 1, r.getEstado() != null ? r.getEstado().name() : null);
        ps.setDouble(desde + 2, r.getMontoTotal());
        ps.setInt(desde + 3, r.getTurista().getIdPersona());
        ps.setInt(desde + 4, r.getPrestadorLocal().getIdPrestador());
        ps.setInt(desde + 5, r.getItinerario().getIdItinerario());
    }
}