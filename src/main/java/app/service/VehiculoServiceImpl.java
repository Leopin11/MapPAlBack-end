package app.service;

import app.domain.Vehiculo;
import app.service.inputport.VehiculoService;
import app.service.outputport.VehiculoRepository;

import java.util.List;
import java.util.Optional;

public class VehiculoServiceImpl implements VehiculoService {

    // Tipo PUERTO (interfaz), nunca la clase concreta → principio D
    private final VehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Optional<Vehiculo> registrarVehiculo(int idVehiculo, String placa, String marca, int capacidadPasajeros, boolean baulCapacidadSilla) {
        // Regla de negocio: no se repite el ID
        if (vehiculoRepository.buscarVehiculoPorId(idVehiculo).isPresent()) {
            return Optional.empty();
        }

        Vehiculo nuevoVehiculo = new Vehiculo(idVehiculo, placa, marca, capacidadPasajeros, baulCapacidadSilla);
        return Optional.of(vehiculoRepository.guardarVehiculo(nuevoVehiculo));
    }

    @Override
    public Optional<Vehiculo> actualizarVehiculo(int idVehiculo, String placa, String marca,  int capacidadPasajeros, boolean baulCapacidadSilla) {

        // Busco el vehículo existente. Si no está, no hay nada que actualizar.
        Optional<Vehiculo> existente = vehiculoRepository.buscarVehiculoPorId(idVehiculo);
        if (existente.isEmpty()) {
            return Optional.empty();
        }

        // MUTO el objeto existente: solo los campos editables.
        //  NO uso "new Vehiculo(...)" → eso borraría rutaSinEscalones.
        //  NO toco el id → es la identidad del vehículo.
        Vehiculo actualizado = existente.get();
        actualizado.setPlaca(placa);
        actualizado.setMarca(marca);
        actualizado.setCapacidadPasajeros(capacidadPasajeros);
        actualizado.setBaulCapacidadSilla(baulCapacidadSilla);

        //Pido al repositorio que persista la nueva versión.
        return vehiculoRepository.actualizarVehiculo(actualizado);
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.vehiculosRegistrados();
    }

    @Override
    public Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo) {
        return vehiculoRepository.buscarVehiculoPorId(idVehiculo);
    }

    @Override
    public boolean eliminarVehiculoPorId(int idVehiculo) {
        // Devuelve true si borró algo, false si ese ID no existía.
        return vehiculoRepository.eliminarVehiculoPorId(idVehiculo);
    }
}