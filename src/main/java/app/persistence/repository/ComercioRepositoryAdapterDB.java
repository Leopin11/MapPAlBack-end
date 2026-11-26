package app.persistence.repository;

import app.domain.Comercio;
import app.persistence.mapper.ComercioMapper;
import app.service.outputport.ComercioRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComercioRepositoryAdapterDB implements ComercioRepository {

    private final Connection dbconeccion;
    private final ComercioMapper comercioMapper;

    public ComercioRepositoryAdapterDB(Connection dbconeccion, ComercioMapper comercioMapper) {
        this.dbconeccion = dbconeccion;
        this.comercioMapper = comercioMapper;
    }

    @Override
    public Comercio guardarComercio(Comercio comercio) {
        // 1. Primero el prestador (la clave foránea lo exige)
        String sqlPrestador = "INSERT INTO PrestadorLocal (idPrestador, tipoPrestador) VALUES (?, 'COMERCIO')";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sqlPrestador)) {
            ps.setInt(1, comercio.getIdComercio());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el prestador del comercio: " + e.getMessage(), e);
        }

        // 2. Después el comercio
        String sql = "INSERT INTO Comercio (idComercio, nombreComercio, tipoComercio, direccion, horarioAtencion, "
                + "tarifaBase, insigniaEspecializada, calificacionPromedio) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, comercio.getIdComercio());
            ps.setString(2, comercio.getNombreComercio());
            ps.setString(3, comercio.getTipoComercio().name());
            ps.setString(4, comercio.getDireccion());
            ps.setString(5, comercio.getHorarioAtencion());
            ps.setDouble(6, comercio.getTarifaBase());
            ps.setBoolean(7, comercio.isInsigniaEspecializada());
            ps.setDouble(8, comercio.getCalificacionPromedio());
            ps.executeUpdate();
            return comercio;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el comercio: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Comercio> comerciosRegistrados() {
        String sql = "SELECT * FROM Comercio";
        List<Comercio> comercios = new ArrayList<>();
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comercios.add(comercioMapper.mapRow(rs));
            }
            return comercios;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los comercios: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Comercio> buscarComercioPorId(int idComercio) {
        String sql = "SELECT * FROM Comercio WHERE idComercio = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idComercio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(comercioMapper.mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el comercio: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Comercio> actualizarComercio(int idComercio, Comercio comercio) {
        String sql = "UPDATE Comercio SET nombreComercio = ?, tipoComercio = ?, direccion = ?, horarioAtencion = ?, "
                + "tarifaBase = ?, insigniaEspecializada = ?, calificacionPromedio = ? WHERE idComercio = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setString(1, comercio.getNombreComercio());
            ps.setString(2, comercio.getTipoComercio().name());
            ps.setString(3, comercio.getDireccion());
            ps.setString(4, comercio.getHorarioAtencion());
            ps.setDouble(5, comercio.getTarifaBase());
            ps.setBoolean(6, comercio.isInsigniaEspecializada());
            ps.setDouble(7, comercio.getCalificacionPromedio());
            ps.setInt(8, idComercio);
            return ps.executeUpdate() > 0 ? Optional.of(comercio) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el comercio: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean eliminarComercioPorId(int idComercio) {
        // 1. Primero el comercio
        String sqlComercio = "DELETE FROM Comercio WHERE idComercio = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sqlComercio)) {
            ps.setInt(1, idComercio);
            if (ps.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el comercio: " + e.getMessage(), e);
        }

        // 2. Después su fila en PrestadorLocal
        String sqlPrestador = "DELETE FROM PrestadorLocal WHERE idPrestador = ? AND tipoPrestador = 'COMERCIO'";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sqlPrestador)) {
            ps.setInt(1, idComercio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el prestador del comercio: " + e.getMessage(), e);
        }
    }
}