package app.persistence.repository;

import app.domain.Itinerario;
import app.domain.enums.NivelEstimulacion;
import app.persistence.mapper.ItinerarioMapper;
import app.service.outputport.ItinerarioRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItinerarioRepositoryAdapterDB implements ItinerarioRepository {

    private final Connection dbconeccion;
    private final ItinerarioMapper itinerarioMapper;

    public ItinerarioRepositoryAdapterDB(Connection dbconeccion, ItinerarioMapper itinerarioMapper) {
        this.dbconeccion = dbconeccion;
        this.itinerarioMapper = itinerarioMapper;
    }

    @Override
    public Itinerario guardarItinerario(Itinerario itinerario) {
        String sql = "INSERT INTO Itinerario (idItinerario, destino, fechaDeInicio, fechaFin, tipoExperiencia, nivelEstimulacion) "
                + "VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, itinerario.getIdItinerario());
            ps.setString(2, itinerario.getDestino());
            ps.setDate(3, Date.valueOf(itinerario.getFechaDeInicio()));
            ps.setDate(4, Date.valueOf(itinerario.getFechaFin()));
            ps.setString(5, itinerario.getTipoExperiencia());
            ps.setString(6, itinerario.getNivelEstimulacion().name());
            ps.executeUpdate();
            return itinerario;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el itinerario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Itinerario> itinerariosRegistrados() {
        String sql = "SELECT * FROM Itinerario";
        List<Itinerario> itinerarios = new ArrayList<>();
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                itinerarios.add(itinerarioMapper.mapRow(rs));
            }
            return itinerarios;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los itinerarios: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Itinerario> buscarItinerarioPorId(int idItinerario) {
        String sql = "SELECT * FROM Itinerario WHERE idItinerario = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idItinerario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(itinerarioMapper.mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el itinerario: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Itinerario> actualizarItinerario(Itinerario itinerario) {
        String sql = "UPDATE Itinerario SET destino = ?, fechaDeInicio = ?, fechaFin = ?, "
                + "tipoExperiencia = ?, nivelEstimulacion = ? WHERE idItinerario = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setString(1, itinerario.getDestino());
            ps.setDate(2, Date.valueOf(itinerario.getFechaDeInicio()));
            ps.setDate(3, Date.valueOf(itinerario.getFechaFin()));
            ps.setString(4, itinerario.getTipoExperiencia());
            ps.setString(5, itinerario.getNivelEstimulacion().name());
            ps.setInt(6, itinerario.getIdItinerario());
            return ps.executeUpdate() > 0 ? Optional.of(itinerario) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el itinerario: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean eliminarItinerarioPorId(int idItinerario) {
        String sql = "DELETE FROM Itinerario WHERE idItinerario = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idItinerario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el itinerario: " + e.getMessage(), e);
        }
    }
}