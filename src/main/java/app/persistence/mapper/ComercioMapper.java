package app.persistence.mapper;

import app.domain.Comercio;
import app.domain.enums.TipoComercio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComercioMapper implements RowMapper<Comercio> {

    @Override
    public Comercio mapRow(ResultSet rs) throws SQLException {
        Comercio comercio = new Comercio();
        comercio.setIdComercio(rs.getInt("idComercio"));
        comercio.setNombreComercio(rs.getString("nombreComercio"));
        comercio.setTipoComercio(TipoComercio.valueOf(rs.getString("tipoComercio")));
        comercio.setDireccion(rs.getString("direccion"));
        comercio.setHorarioAtencion(rs.getString("horarioAtencion"));
        comercio.setTarifaBase(rs.getDouble("tarifaBase"));
        comercio.setInsigniaEspecializada(rs.getBoolean("insigniaEspecializada"));
        comercio.setCalificacionPromedio(rs.getDouble("calificacionPromedio"));
        return comercio;
    }
}