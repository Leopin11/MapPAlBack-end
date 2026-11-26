package app.persistence.mapper;

import app.domain.Itinerario;
import app.domain.enums.NivelEstimulacion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItinerarioMapper implements RowMapper<Itinerario> {

    @Override
    public Itinerario mapRow(ResultSet rs) throws SQLException {
        Itinerario itinerario = new Itinerario();
        itinerario.setIdItinerario(rs.getInt("idItinerario"));
        itinerario.setDestino(rs.getString("destino"));
        itinerario.setFechaDeInicio(rs.getDate("fechaDeInicio").toLocalDate());
        itinerario.setFechaFin(rs.getDate("fechaFin").toLocalDate());
        itinerario.setTipoExperiencia(rs.getString("tipoExperiencia"));
        itinerario.setNivelEstimulacion(NivelEstimulacion.valueOf(rs.getString("nivelEstimulacion")));
        return itinerario;
    }
}