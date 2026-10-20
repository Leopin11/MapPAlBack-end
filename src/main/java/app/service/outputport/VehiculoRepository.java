package app.service.outputport;

import app.domain.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository {
    public Vehiculo guardarVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> vehiculosRegistrados();
    public Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo);
    public Optional<Vehiculo> actualizarVehiculo(Vehiculo vehiculo);
    public boolean eliminarVehiculoPorId(int idVehiculo);
}
