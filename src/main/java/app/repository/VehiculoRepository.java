package app.repository;

import app.domain.Vehiculo;

import java.util.ArrayList;
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
        return this.vehiculos;
    }

    public Vehiculo buscarVehiculoPorId(Integer idVehiculo){

        for (Vehiculo vehiculo: this.vehiculos){
            if (vehiculo.getIdVehiculo().equals(idVehiculo)){
                return vehiculo;
            }
        }

        return null;
    }

    public void eliminarVehiculo(Integer idVehiculo){
        this.vehiculos.removeIf(vehiculo -> vehiculo.getIdVehiculo().equals(idVehiculo));
    }
}
