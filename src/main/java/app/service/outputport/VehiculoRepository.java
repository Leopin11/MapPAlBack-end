package app.service.outputport;

import app.domain.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository {
    Vehiculo guardarVehiculo(Vehiculo vehiculo);
    List<Vehiculo> vehiculosRegistrados();
    Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo);
    Optional<Vehiculo> actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculoPorId(int idVehiculo);
}
