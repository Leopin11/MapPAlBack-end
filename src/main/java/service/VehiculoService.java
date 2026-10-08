package service;

import domain.Vehiculo;
import repository.VehiculoRepository;

import java.util.List;

public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    //Registro de vehiculo
    public void registrarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            System.out.println("No puedes registrar un vehiculo nulo");
            return;
        }

        List<Vehiculo> vehiculoARegistrar = vehiculoRepository.vehiculosRegistrados();

        for (Vehiculo cadaVehiculo : vehiculoARegistrar){
            if (vehiculo.getIdVehiculo().equals(cadaVehiculo.getIdVehiculo())){
                System.out.println("No puedes registrar un vehiculo nuevo con un id existente" ); ;
                return;
            }
        }

        System.out.println("Vehiculo se registro con exito");
        vehiculoRepository.guardarVehiculo(vehiculo);

    }

    //Mostrar todos los vehiculos.
    public List<Vehiculo> mostrarVehiculos() {
        return vehiculoRepository.vehiculosRegistrados();
    }

    //Mostrar por el id.
    public Vehiculo buscarId(Integer idVehiculo) {
        return vehiculoRepository.buscarVehiculoPorId(idVehiculo);
    }

    //Eliminar por el id.
    public void eliminar(Integer idVehiculo){
        vehiculoRepository.eliminarVehiculo(idVehiculo);
    }

}
