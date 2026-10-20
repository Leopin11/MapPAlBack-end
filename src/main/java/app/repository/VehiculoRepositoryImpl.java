package app.repository;

import app.domain.Vehiculo;
import app.service.outputport.VehiculoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VehiculoRepositoryImpl implements VehiculoRepository {
    private final  List<Vehiculo> vehiculos;

    public VehiculoRepositoryImpl() {
        this.vehiculos = new ArrayList<>();
    }


    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        return vehiculo;
    }

    @Override
    public List<Vehiculo> vehiculosRegistrados() {
        return Collections.unmodifiableList(this.vehiculos);
    }

    @Override
    public Optional<Vehiculo> buscarVehiculoPorId(int idVehiculo) {
        for (Vehiculo vehiculo : this.vehiculos) {
            if (vehiculo.getIdVehiculo() == idVehiculo) {
                return Optional.of(vehiculo);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Vehiculo> actualizarVehiculo(Vehiculo vehiculo) {
        for (int i = 0; i < this.vehiculos.size(); i++) {
            if (this.vehiculos.get(i).getIdVehiculo() == vehiculo.getIdVehiculo()) {
                this.vehiculos.set(i, vehiculo);
                return Optional.of(vehiculo);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarVehiculoPorId(int idVehiculo) {
        // removeIf ya devuelve true si eliminó al menos un elemento.
        return this.vehiculos.removeIf(vehiculo -> vehiculo.getIdVehiculo() == idVehiculo);
    }
}
