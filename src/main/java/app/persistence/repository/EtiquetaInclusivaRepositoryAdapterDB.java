package app.persistence.repository;

import app.domain.EtiquetaInclusiva;
import app.domain.Vehiculo;
import app.domain.enums.CategoriaEtiqueta;
import app.persistence.mapper.EtiquetaInclusivaMapper;
import app.service.outputport.EtiquetaInclusivaRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtiquetaInclusivaRepositoryAdapterDB implements EtiquetaInclusivaRepository {

    private final Connection dbconeccion;
    private final EtiquetaInclusivaMapper etiquetaInclusivaMapper;

    public EtiquetaInclusivaRepositoryAdapterDB(Connection dbconeccion, EtiquetaInclusivaMapper etiquetaInclusivaMapper) {
        this.dbconeccion = dbconeccion;
        this.etiquetaInclusivaMapper = etiquetaInclusivaMapper;

    }

    @Override
    public EtiquetaInclusiva guardarEtiqueta(EtiquetaInclusiva etiquetaInclusiva) {
        String sql = "INSERT INTO EtiquetaInclusiva (idEtiqueta, nombre, categoria, descripcion) VALUES (?,?,?,?)";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            setEtiquetaInclusivaParams(ps, etiquetaInclusiva);
            ps.executeUpdate();
            System.out.println("Se registro Etiqueta en la Base de datos");
        } catch (SQLException e) {
            throw  new RuntimeException("Error al guardar la Etiqueta" + e.getMessage());
        }

        return etiquetaInclusiva;
    }



    @Override
    public List<EtiquetaInclusiva> etiquetasRegistradas() {
        List<EtiquetaInclusiva> etiquetaInclusivas = new ArrayList<>();

        String sql = "SELECT * FROM EtiquetaInclusiva";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                etiquetaInclusivas.add(etiquetaInclusivaMapper.mapRow(rs));
            }

        } catch(SQLException e){
            throw  new RuntimeException("Error al consultar las etiquetas " + e.getMessage());
        }
        return etiquetaInclusivas;
    }

    @Override
    public Optional<EtiquetaInclusiva> buscarIdEtiquetas(int idEtiqueta) {
        String sql = "SELECT * FROM EtiquetaInclusiva WHERE idEtiqueta = ?";

        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idEtiqueta);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return  Optional.of(etiquetaInclusivaMapper.mapRow(rs));
            }
            ;
        } catch(SQLException e){
            throw  new RuntimeException("Error al encontrar la etiqueta por su ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<EtiquetaInclusiva> actualizarEtiqueta(int idEtiqueta, EtiquetaInclusiva etiquetaInclusiva) {
        String sql = "UPDATE EtiquetaInclusiva SET nombre = ?, categoria = ?, descripcion = ? WHERE idEtiqueta =?";

        try (PreparedStatement ps = dbconeccion.prepareStatement(sql)){
            ps.setString(1,etiquetaInclusiva.getNombre());
            ps.setString(2,etiquetaInclusiva.getCategoria());
            ps.setString(3,etiquetaInclusiva.getDescripcion());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0){
                return Optional.empty();
            }
            return Optional.of(etiquetaInclusiva);

        } catch (SQLException e) {
            throw new RuntimeException("No se feu posible actualizar la etiqueta" + e.getMessage());
        }
    }

    @Override
    public boolean eliminarEtiqueta(int idEtiqueta) {
        String sql = "DELETE FROM EtiquetaInclusiva WHERE idEtiqueta = ? ";
        try(PreparedStatement ps = dbconeccion.prepareStatement(sql)) {
            ps.setInt(1, idEtiqueta);

            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas != 0) {
                System.out.println("etiqueta eliminada con exito");
                return true;
            }

        } catch(SQLException e){
            throw  new RuntimeException("Error al eliminar la etiqueta" + e.getMessage());
        }
        return false;
    }
    private void setEtiquetaInclusivaParams(PreparedStatement ps, EtiquetaInclusiva etiquetaInclusiva) throws SQLException {
        ps.setInt(1, etiquetaInclusiva.getIdEtiqueta());
        ps.setString(2,etiquetaInclusiva.getNombre());
        ps.setString(2,etiquetaInclusiva.getCategoria());
        ps.setString(3,etiquetaInclusiva.getDescripcion());
    }
}
