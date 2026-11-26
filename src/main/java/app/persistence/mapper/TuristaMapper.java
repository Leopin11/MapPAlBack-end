package app.persistence.mapper;

import app.domain.Turista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class TuristaMapper implements RowMapper<Turista> {
    @Override
    public Turista mapRow(ResultSet rs) throws SQLException {
        Turista turista = new Turista();

        turista.setIdPersona(rs.getInt("idPersona"));
        turista.setNombre(rs.getString("nombre"));
        turista.setApellido(rs.getString("apellido"));
        turista.setDocumentoId(rs.getString("documentoId"));
        turista.setTelefono(rs.getString("telefono"));
        turista.setEmail(rs.getString("email"));
        turista.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        turista.setActivo(rs.getBoolean("activo"));
        turista.setNacionalidad(rs.getString("nacionalidad"));
        turista.setIdiomaPreferido(rs.getString("idiomaPreferido"));

        return turista;
    }
}
