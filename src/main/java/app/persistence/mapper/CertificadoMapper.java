package app.persistence.mapper;

import app.domain.Certificado;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificadoMapper implements RowMapper<Certificado>{

    @Override
    public Certificado mapRow(ResultSet rs) throws SQLException {
        Certificado certificado = new Certificado();

        certificado.setIdCertificado(rs.getInt("idCertificado"));
        certificado.setNombre(rs.getString("nombre"));
        certificado.setEntidadEmisora(rs.getString("entidadEmisora"));
        certificado.setFechaObtencion(rs.getDate("fechaObtencion").toLocalDate());

        return certificado;
    }
}
