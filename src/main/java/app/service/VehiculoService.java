package app.service;

import app.domain.Vehiculo;
import app.repository.VehiculoRepository;

import java.util.List;

public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    //Registro de vehiculo
    public void registrarVehiculo(int idVehiculo, String placa, String marca, int capacidadPasejeros, boolean baulCapacidadSilla) {

        // Se valida si el id ya existe en la capa de datos.
        List<Vehiculo> vehiculosRegistrados = vehiculoRepository.vehiculosRegistrados();

        for (Vehiculo cadaVehiculo : vehiculosRegistrados){
            if (idVehiculo == cadaVehiculo.getIdVehiculo()){
                System.out.println("Error: No puedes registrar un vehiculo nuevo con ID que ya existe.");
                return;
            }
        }

        // Paso la validacion se instancia la entidad del dominio
        Vehiculo nuevoVehiculo = new Vehiculo(idVehiculo,placa,marca,capacidadPasejeros,baulCapacidadSilla);

        //guardar en el repositorio
        vehiculoRepository.guardarVehiculo(nuevoVehiculo);
        System.out.println("Vehiculo creado y registrado con exito");
}

    //Mostrar todos los vehiculos.
    public List<Vehiculo> mostrarVehiculos() {
        System.out.println("Vehiculos Registrados:");
        return vehiculoRepository.vehiculosRegistrados();


    }

    //Mostrar por el id.
    public Vehiculo buscarId(int idVehiculo) {
        System.out.println("Consultando informacion para el ID: " + idVehiculo);
        return vehiculoRepository.buscarVehiculoPorId(idVehiculo);
    }

    //Eliminar por el id.
    public void eliminar(int idVehiculo){
        System.out.println("Eliminar Vehiculo con ID: " + idVehiculo);
        vehiculoRepository.eliminarVehiculo(idVehiculo);
    }

}
