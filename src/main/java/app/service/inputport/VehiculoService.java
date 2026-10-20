package app.service.inputport;

import app.domain.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    Optional<Vehiculo> registrarVehiculo(int idVehiculo, String placa, String marca, int capacidadPasajeros, boolean baulCapacidadSilla);
    Optional<Vehiculo> actualizarVehiculo(int idVehiculo, String placa, String marca, int capacidadPasajeros, boolean baulCapacidadSilla);
    List<Vehiculo> listarVehiculos();
    Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo);
    boolean eliminarVehiculoPorId(int idVehiculo);
}
