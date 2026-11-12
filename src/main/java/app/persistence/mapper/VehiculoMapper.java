package app.persistence.mapper;

import app.domain.Vehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoMapper implements  RowMapper<Vehiculo> {

    @Override
    public Vehiculo mapRow(ResultSet rs) throws SQLException {
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
        vehiculo.setPlaca(rs.getString("placa"));
        vehiculo.setMarca(rs.getString("Marca"));
        vehiculo.setCapacidadPasajeros(rs.getInt("capacidadPasajeros"));
        vehiculo.setBaulCapacidadSilla(rs.getBoolean("baulCapacidadSilla"));
        vehiculo.setRutaSinEscalones(rs.getBoolean("rutaSinEscalones"));

        return vehiculo;
    }


}
