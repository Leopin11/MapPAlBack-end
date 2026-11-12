package app.persistence.mapper;
import app.domain.enums.CategoriaEtiqueta;
import app.domain.relations.VehiculoEtiqueta;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoEtiquetaMapper implements RowMapper<VehiculoEtiqueta> {

    @Override
    public VehiculoEtiqueta mapRow(ResultSet rs) throws SQLException {
        VehiculoEtiqueta vehiculoEtiqueta = new VehiculoEtiqueta();

        vehiculoEtiqueta.setIdVehiculo(rs.getInt("idVehiculo"));
        vehiculoEtiqueta.setIdEtiqueta(rs.getInt("idEtiqueta"));
        vehiculoEtiqueta.setNombreEtiqueta(rs.getString("nombreEtiqueta"));
        vehiculoEtiqueta.setCategoria(CategoriaEtiqueta.valueOf(rs.getString("categoria")));

        return vehiculoEtiqueta;
    }
}
