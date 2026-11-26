package app.persistence.mapper;

import app.domain.GuiaConductor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuiaConductorMapper implements RowMapper<GuiaConductor> {

    @Override
    public GuiaConductor mapRow(ResultSet rs) throws SQLException {
        GuiaConductor guia = new GuiaConductor();
        guia.setIdPersona(rs.getInt("idPersona"));
        guia.setNombre(rs.getString("nombre"));
        guia.setApellido(rs.getString("apellido"));
        guia.setDocumentoId(rs.getString("documentoId"));
        guia.setTelefono(rs.getString("telefono"));
        guia.setEmail(rs.getString("email"));
        guia.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        guia.setActivo(rs.getBoolean("activo"));
        guia.setTarifaBase(rs.getDouble("tarifaBase"));
        guia.setInsigniaEspecializada(rs.getBoolean("insigniaEspecializada"));
        guia.setCalificacionPromedio(rs.getDouble("calificacionPromedio"));
        return guia;
    }
}