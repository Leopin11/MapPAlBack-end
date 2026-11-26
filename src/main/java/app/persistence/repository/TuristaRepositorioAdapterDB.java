package app.persistence.repository;

import app.domain.Turista;
import app.persistence.mapper.TuristaMapper;
import app.service.outputport.TuristaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TuristaRepositorioAdapterDB implements TuristaRepository {
    private final Connection dbconeccion ;
    private final TuristaMapper turistaMapper;

    public TuristaRepositorioAdapterDB(Connection dbconeccion, TuristaMapper turistaMapper) {
        this.dbconeccion = dbconeccion;
        this.turistaMapper = turistaMapper;
    }

    @Override
    public Turista guardarTurista(Turista turista) {
        String sql = "INSERT INTO Turista (idPersona, nombre, apellido, documentoId, telefono, email, fechaNacimiento, activo, nacionalidad, idiomaPreferido) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            setTuristaParams(ps, turista);
            ps.executeUpdate();
            System.out.println("Se registro turista en la Base de datos");
        } catch (SQLException e) {
            throw  new RuntimeException("Error al guardar el Turista" + e.getMessage());
        }

        return turista;
    }



    @Override
    public List<Turista> TuristasRegistrados() {
        List<Turista> turistas = new ArrayList<>();

        String sql = "SELECT * FROM Turista";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                turistas.add(turistaMapper.mapRow(rs));
            }

        } catch(SQLException e){
            throw  new RuntimeException("Error al consultar los turistas " + e.getMessage());
        }
        return turistas;
    }

    @Override
    public Optional<Turista> buscarTuristaPorId(int idTurista) {
        String sql = "SELECT * FROM Turista WHERE idPersona = ?";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idTurista);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return  Optional.of(turistaMapper.mapRow(rs));
            }
            ;
        } catch(SQLException e){
            throw  new RuntimeException("Error al encontrar el turista por su ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Turista> actualizarTurista(Turista turista) {
        String sql = "UPDATE Turista SET nombre = ?, apellido = ?, documentoId = ?, telefono = ?, email = ?, "
                + "fechaNacimiento = ?, activo = ?, nacionalidad = ?, idiomaPreferido = ? WHERE idPersona = ?";

        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setString(1, turista.getNombre());
            ps.setString(2, turista.getApellido());
            ps.setString(3, turista.getDocumentoId());
            ps.setString(4, turista.getTelefono());
            ps.setString(5, turista.getEmail());
            ps.setDate(6, Date.valueOf(turista.getFechaNacimiento()));
            ps.setBoolean(7, turista.isActivo());
            ps.setString(8, turista.getNacionalidad());
            ps.setString(9, turista.getIdiomaPreferido());
            ps.setInt(10, turista.getIdPersona());



            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return Optional.empty();
            }

            return Optional.of(turista);

        } catch(SQLException e){
            throw  new RuntimeException("No se pudo actualizar el turista " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarTuristaPorId(int idTurista) {
            String sql = "DELETE FROM Turista WHERE idPersona = ? ";
            try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
                ps.setInt(1, idTurista);

                int filasAfectadas = ps.executeUpdate();
                if(filasAfectadas != 0) {
                    System.out.println("Turista eliminado con exito");
                    return true;
                }

            } catch(SQLException e){
                throw  new RuntimeException("Error al eliminar el Turista" + e.getMessage());
            }
            return false;
    }
    private void setTuristaParams(PreparedStatement ps, Turista turista) throws SQLException{
        ps.setInt(1, turista.getIdPersona());
        ps.setString(2, turista.getNombre());
        ps.setString(3, turista.getApellido());
        ps.setString(4, turista.getDocumentoId());
        ps.setString(5, turista.getTelefono());
        ps.setString(6, turista.getEmail());
        ps.setDate(7, Date.valueOf(turista.getFechaNacimiento()));
        ps.setBoolean(8, turista.isActivo());
        ps.setString(9, turista.getNacionalidad());
        ps.setString(10, turista.getIdiomaPreferido());
    }
}
