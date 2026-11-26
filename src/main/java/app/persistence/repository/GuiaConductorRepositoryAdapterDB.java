package app.persistence.repository;

import app.domain.GuiaConductor;
import app.persistence.mapper.GuiaConductorMapper;
import app.service.outputport.GuiaConductorRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuiaConductorRepositoryAdapterDB implements GuiaConductorRepository {

    private final Connection dbconeccion;
    private final GuiaConductorMapper guiaConductorMapper;

    public GuiaConductorRepositoryAdapterDB(Connection dbconeccion, GuiaConductorMapper guiaConductorMapper) {
        this.dbconeccion = dbconeccion;
        this.guiaConductorMapper = guiaConductorMapper;
    }

    @Override
    public GuiaConductor guardarGuiaConductor(GuiaConductor guia) {
        String sqlPrestador = "INSERT INTO PrestadorLocal (idPrestador, tipoPrestador) VALUES (?, 'GUIA')";
        String sqlGuia = "INSERT INTO GuiaConductor (idPersona, nombre, apellido, documentoId, telefono, email, "
                + "fechaNacimiento, activo, tarifaBase, insigniaEspecializada, calificacionPromedio) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement psPrestador = dbconeccion.prepareStatement(sqlPrestador)) {
            psPrestador.setInt(1, guia.getIdPersona());
            psPrestador.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el prestador del guía: " + e.getMessage(), e);
        }
        try (PreparedStatement ps = dbconeccion.prepareStatement(sqlGuia)) {
            ps.setInt(1, guia.getIdPersona());
            ps.setString(2, guia.getNombre());
            ps.setString(3, guia.getApellido());
            ps.setString(4, guia.getDocumentoId());
            ps.setString(5, guia.getTelefono());
            ps.setString(6, guia.getEmail());
            ps.setDate(7, Date.valueOf(guia.getFechaNacimiento()));
            ps.setBoolean(8, guia.isActivo());
            ps.setDouble(9, guia.getTarifaBase());
            ps.setBoolean(10, guia.isInsigniaEspecializada());
            ps.setDouble(11, guia.getCalificacionPromedio());
            ps.executeUpdate();
            return guia;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el GuiaConductor: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<GuiaConductor> buscarPorId(int idPersona) {
        String sql = "SELECT * FROM GuiaConductor WHERE idPersona = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(guiaConductorMapper.mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el GuiaConductor: " + e.getMessage(), e);
        }
    }

    @Override
    public List<GuiaConductor> listarTodos() {
        String sql = "SELECT * FROM GuiaConductor";
        List<GuiaConductor> guias = new ArrayList<>();
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                guias.add(guiaConductorMapper.mapRow(rs));
            }
            return guias;
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los GuiaConductor: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<GuiaConductor> actualizarGuiaConductor(GuiaConductor guia) {
        String sql = "UPDATE GuiaConductor SET nombre = ?, apellido = ?, documentoId = ?, telefono = ?, email = ?, "
                + "fechaNacimiento = ?, activo = ?, tarifaBase = ?, insigniaEspecializada = ?, "
                + "calificacionPromedio = ? WHERE idPersona = ?";
        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setString(1, guia.getNombre());
            ps.setString(2, guia.getApellido());
            ps.setString(3, guia.getDocumentoId());
            ps.setString(4, guia.getTelefono());
            ps.setString(5, guia.getEmail());
            ps.setDate(6, Date.valueOf(guia.getFechaNacimiento()));
            ps.setBoolean(7, guia.isActivo());
            ps.setDouble(8, guia.getTarifaBase());
            ps.setBoolean(9, guia.isInsigniaEspecializada());
            ps.setDouble(10, guia.getCalificacionPromedio());
            ps.setInt(11, guia.getIdPersona());
            return ps.executeUpdate() > 0 ? Optional.of(guia) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el GuiaConductor: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean eliminarGuiaConductor(int idPersona) {
        try (PreparedStatement psGuia = dbconeccion.prepareStatement("DELETE FROM GuiaConductor WHERE idPersona = ?")) {
            psGuia.setInt(1, idPersona);
            boolean eliminado = psGuia.executeUpdate() > 0;
            if (!eliminado) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el GuiaConductor: " + e.getMessage(), e);
        }
        try (PreparedStatement psPrestador = dbconeccion.prepareStatement(
                "DELETE FROM PrestadorLocal WHERE idPrestador = ? AND tipoPrestador = 'GUIA'")) {
            psPrestador.setInt(1, idPersona);
            return psPrestador.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el prestador del guía: " + e.getMessage(), e);
        }
    }
}