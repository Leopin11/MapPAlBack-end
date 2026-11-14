package app.persistence.repository;

import app.domain.Vehiculo;
import app.domain.relations.VehiculoEtiqueta;
import app.persistence.mapper.VehiculoEtiquetaMapper;
import app.persistence.mapper.VehiculoMapper;
import app.service.outputport.VehiculoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiculoRepositoryAdapterDB implements VehiculoRepository {

    private final Connection dbconeccion;
    private final VehiculoMapper vehiculoMapper;
    private final VehiculoEtiquetaMapper vehiculoEtiquetaMapper;

    public VehiculoRepositoryAdapterDB(Connection dbconeccion, VehiculoMapper vehiculoMapper, VehiculoEtiquetaMapper vehiculoEtiquetaMapper) {
        this.dbconeccion = dbconeccion;
        this.vehiculoMapper = vehiculoMapper;
        this.vehiculoEtiquetaMapper = vehiculoEtiquetaMapper;
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculo (idVehiculo, placa, marca, capacidadPasajeros, baulCapacidadSilla, rutaSinEscalones) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            setVehiculoParams(ps, vehiculo);
            ps.executeUpdate();
            System.out.println("Se registro Vehiculo en la Base de datos");
        } catch (SQLException e) {
            throw  new RuntimeException("Error al guardar el Vehiculo" + e.getMessage());
        }

        return vehiculo;
    }

    @Override
    public List<Vehiculo> vehiculosRegistrados() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        String sql = "SELECT * FROM Vehiculo";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                vehiculos.add(vehiculoMapper.mapRow(rs));
            }

        } catch(SQLException e){
            throw  new RuntimeException("Error al consultar los vehiculos " + e.getMessage());
        }
        return vehiculos;
    }

    @Override
    public Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo) {
        String sql = "SELECT * FROM Vehiculo WHERE idVehiculo = ?";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return  Optional.of(vehiculoMapper.mapRow(rs));
            }
                    ;
        } catch(SQLException e){
            throw  new RuntimeException("Error al encontrar el vehículo por su ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Vehiculo> actualizarVehiculo(int idVehiculo, Vehiculo vehiculo) {
        String sql = "UPDATE  Vehiculo SET placa = ?, marca = ?, capacidadPasajeros = ?, baulCapacidadSilla = ?, rutaSinEscalones = ? WHERE idVehiculo = ?";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getMarca());
            ps.setInt(3,    vehiculo.getCapacidadPasajeros());
            ps.setBoolean(4, vehiculo.isBaulCapacidadSilla());
            ps.setBoolean(5, vehiculo.isRutaSinEscalones());
            ps.setInt(6,    idVehiculo);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return Optional.empty();
            }

            return Optional.of(vehiculo);

        } catch(SQLException e){
            throw  new RuntimeException("No se pudo actualizar el certificado " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarVehiculoPorId(int idVehiculo) {
        String sql = "DELETE FROM Vehiculo WHERE idVehiculo = ? ";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);

            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas != 0) {
                System.out.println("Vehiculo eliminado con exito");
                return true;
            }

        } catch(SQLException e){
            throw  new RuntimeException("Error al eliminar el vehiculo" + e.getMessage());
        }
        return false;
    }

    @Override
    public List<VehiculoEtiqueta> obtenerEtiquetasDeVehiculo(int idVehiculo) {
        String sql = """
                SELECT ve.idVehiculo, ve.idEtiqueta, e.nombre AS nombreEtiqueta, e.categoriaEtiqueta AS categoria
                FROM VehiculoEtiqueta ve
                INNER JOIN EtiquetaInclusiva e ON ve.idEtiqueta = e.idEtiqueta
                WHERE ve.idVehiculo = ?
               """;
        List<VehiculoEtiqueta> vehiculoEtiquetas = new ArrayList<>();

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                vehiculoEtiquetas.add(vehiculoEtiquetaMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar etiquetas del vehículo" + e.getMessage());        }
        return vehiculoEtiquetas;
    }

    private void setVehiculoParams(PreparedStatement ps, Vehiculo vehiculo) throws SQLException{
       ps.setInt(1, vehiculo.getIdVehiculo());
       ps.setString(2, vehiculo.getPlaca());
       ps.setString(3, vehiculo.getMarca());
       ps.setInt(4, vehiculo.getCapacidadPasajeros());
       ps.setBoolean(5, vehiculo.isBaulCapacidadSilla());
       ps.setBoolean(6, vehiculo.isRutaSinEscalones());
    }
}
