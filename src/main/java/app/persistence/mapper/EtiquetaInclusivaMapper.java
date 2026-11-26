package app.persistence.mapper;

import app.domain.EtiquetaInclusiva;
import app.domain.enums.CategoriaEtiqueta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EtiquetaInclusivaMapper implements RowMapper<EtiquetaInclusiva> {


    @Override
    public EtiquetaInclusiva mapRow(ResultSet rs) throws SQLException {
        EtiquetaInclusiva etiquetaInclusiva = new EtiquetaInclusiva();

        etiquetaInclusiva.setIdEtiqueta(rs.getInt("idEtiqueta"));
        etiquetaInclusiva.setNombre(rs.getString("nombre"));
        etiquetaInclusiva.setCategoria((CategoriaEtiqueta) rs.getObject("categoria"));
        etiquetaInclusiva.setDescripcion(rs.getString("descripcion"));
        return etiquetaInclusiva;
    }
}
