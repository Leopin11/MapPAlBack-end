package app.persistence.repository;

import app.domain.Reserva;
import app.persistence.mapper.ReservaMapper;
import app.service.outputport.PrestadorLocalRepository;
import app.service.outputport.ReservaRepository;
import app.service.outputport.TuristaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryAdapterDB implements ReservaRepository {

    private final Connection conexion;
    private final ReservaMapper reservaMapper;
    private final TuristaRepository turistaRepository;
    private final PrestadorLocalRepository prestadorLocalRepository;
    private final ItinerarioRepository itinerarioRepository;


    public ReservaRepositoryAdapterDB(Connection conexion, ReservaMapper reservaMapper, TuristaRepository turistaRepository, PrestadorLocalRepository prestadorLocalRepository, ItinerarioRepository itinerarioRepository) {
        this.conexion = conexion;
        this.reservaMapper = reservaMapper;
        this.turistaRepository = turistaRepository;
        this.prestadorLocalRepository = prestadorLocalRepository;
        this.itinerarioRepository = itinerarioRepository;
    }
    private record FilaReserva(Reserva reserva, int idTurista, int idPrestador, int idItinerario) {}

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (IdReserva, Fecha_reserva, Estado_reserva, Monto_total, Turista, Prestador_local, Itinerario) "
                + "VALUES (?,?,?,?,?,?,?)";
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
        String sql = "SELECT * FROM Reserva";
        List<FilaReserva> filas = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                filas.add(leerFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las reservas: " + e.getMessage(), e);
        }

        // Se resuelven las relaciones después de cerrar el ResultSet
        List<Reserva> reservas = new ArrayList<>();
        for (FilaReserva fila : filas) {
            reservas.add(resolverRelaciones(fila));
        }
        return reservas;
    }
    @Override
    public Optional<Reserva> buscarReservaPorId(int idReserva) {
        String sql = "SELECT * FROM Reserva WHERE idReserva = ?";
        FilaReserva fila = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fila = leerFila(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la reserva: " + e.getMessage(), e);
        }

        return fila == null ? Optional.empty() : Optional.of(resolverRelaciones(fila));
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
        String sql = "DELETE FROM Reserva WHERE idReserva = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage(), e);
        }
    }
    private void setCamposComunes(PreparedStatement ps, Reserva r, int desde) throws SQLException {
        ps.setObject(desde, r.getFechaReserva());
        ps.setString(desde + 1, r.getEstado() != null ? r.getEstado().name() : null);
        ps.setDouble(desde + 2, r.getMontoTotal());
        ps.setInt(desde + 3, r.getTurista().getIdPersona());
        ps.setInt(desde + 4, r.getPrestadorLocal().getIdPrestador());
        ps.setInt(desde + 5, r.getItinerario().getIdItinerario());
    }

    private FilaReserva leerFila(ResultSet rs) throws SQLException {
        return new FilaReserva(
                reservaMapper.mapRow(rs),
                rs.getInt("Turista"),
                rs.getInt("Prestador_local"),
                rs.getInt("Itinerario"));
    }

    private Reserva resolverRelaciones(FilaReserva fila) {
        Reserva r = fila.reserva();
        r.setTurista(turistaRepository.buscarTuristaPorId(fila.idTurista()).orElse(null));
        r.setPrestadorLocal(prestadorLocalRepository.buscarPrestadorPorId(fila.idPrestador()).orElse(null));
        r.setItinerario(itinerarioRepository.buscarIdItinerarios(fila.idItinerario()));
        return r;
    }
}
