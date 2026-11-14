package app.persistence.repository;

import app.domain.Certificado;
import app.persistence.mapper.CertificadoMapper;
import app.service.outputport.CertificadoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CertificadoRepositoryAdapterDB implements CertificadoRepository {

    private final Connection dbconeccion;
    private final CertificadoMapper certificadoMapper;

    public CertificadoRepositoryAdapterDB(Connection dbconeccion, CertificadoMapper certificadoMapper) {
        this.dbconeccion = dbconeccion;
        this.certificadoMapper = certificadoMapper;
    }


    @Override
    public Certificado guardarCertificado(Certificado certificado) {
        String sql = "INSERT INTO Certificado (idCertificado, nombre, entidadEmisora, fechaObtencion) VALUES (?,?,?,?)";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            setCertificadoParams(ps, certificado);
            ps.executeUpdate();
            System.out.println("Se registro el Certificado en la Base de Datos");
        }catch (SQLException e) {
            throw new RuntimeException("Error al guardar el Certificado" + e.getMessage());
        }

        return certificado;
    }

    @Override
    public List<Certificado> certificadosRegistrados() {
        List<Certificado> certificados = new ArrayList<>();

        String sql = "SELECT * FROM Certificado";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                certificados.add(certificadoMapper.mapRow(rs));
            }

        }catch (SQLException e) {
            throw new RuntimeException ("Error al consultar los Certificados" + e.getMessage());
        }

        return certificados;
    }

    @Override
    public Optional<Certificado> buscarCertificadoPorId(int idCertificado) {
        String sql = "SELECT * FROM Certificado WHERE idCertificado = ?";

        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)){
            ps.setInt(1, idCertificado);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return Optional.of(certificadoMapper.mapRow(rs));
            }

        }catch (SQLException e) {
            throw new RuntimeException("Error al encontrar el certificado por su ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Certificado> actualizarCertificado(int idCertificado, Certificado certificado) {
        String sql = "UPDATE Certificado SET nombre = ?, entidadEmisora = ?, fechaObtencion = ? WHERE idCertificado = ? ";

        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)){
            ps.setString(1, certificado.getNombre());
            ps.setString(2, certificado.getEntidadEmisora());
            ps.setDate(3, Date.valueOf(certificado.getFechaObtencion()));
            ps.setInt(4, idCertificado);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return Optional.empty();
            }

            return Optional.of(certificado);

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo actualizar el certificado " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarCertificadoPorId(int idCertificado) {
        String sql = "DELETE FROM Certificado WHERE idCertificado = ?";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idCertificado);

            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas != 0) {
                System.out.println("Certificado eliminado con exito");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo eliminar el certificado por su ID " + e.getMessage());
        }
        return false;
    }

    private void setCertificadoParams(PreparedStatement ps, Certificado certificado) throws SQLException {
        ps.setInt(1, certificado.getIdCertificado());
        ps.setString(2, certificado.getNombre());
        ps.setString(3, certificado.getEntidadEmisora());
        ps.setDate(4, Date.valueOf(certificado.getFechaObtencion()));
    };
}
