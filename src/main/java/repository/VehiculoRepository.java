package repository;

import domain.Vehiculo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VehiculoRepository {

    private final List<Vehiculo> vehiculos;

    public VehiculoRepository(){
        this.vehiculos = new ArrayList<>();
    }

    public void guardarVehiculo(Vehiculo vehiculo){
        this.vehiculos.add(vehiculo);
    }

    public List<Vehiculo> vehiculosRegistrados(){
        return Collections.unmodifiableList(this.vehiculos);
    }

    public Vehiculo buscarVehiculoPorId(int idVehiculo){

        for (Vehiculo vehiculo: this.vehiculos){
            if (vehiculo.getIdVehiculo() == idVehiculo){
                return vehiculo;
            }
        }

        return null;
    }

    public void eliminarVehiculo(int idVehiculo){
        this.vehiculos.removeIf(vehiculo -> vehiculo.getIdVehiculo() == idVehiculo);
    }
}
