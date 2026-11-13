package app.persistence.mapper;

import app.domain.Itinerario;
import app.domain.PrestadorLocal;
import app.domain.Reserva;
import app.domain.Turista;
import app.domain.enums.EstadoReserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReservaMapper implements RowMapper<Reserva> {
    @Override
    public Reserva mapRow(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(rs.getInt("IdReserva"));
        reserva.setFechaReserva(rs.getDate("Fecha_reserva").toLocalDate().atStartOfDay());

        String estado = rs.getString("Estado_reserva");
        reserva.setEstado(estado != null ? EstadoReserva.valueOf(estado) : null);

        reserva.setMontoTotal(rs.getDouble("Monto_total"));
        return reserva;
    }
}
