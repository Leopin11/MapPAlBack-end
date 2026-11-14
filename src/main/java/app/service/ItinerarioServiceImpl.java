package app.service;

import app.domain.Itinerario;
import app.service.outputport.ItinerarioRepository;
import app.service.inputport.ItinerarioService;

import java.util.List;
import java.util.Optional;

public class ItinerarioServiceImpl implements ItinerarioService {
    private final ItinerarioRepository itinerarioRepository;

    public ItinerarioServiceImpl(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }


    //PRIMER MÉT.ODO registrarItinerario():
    @Override
    public Optional<Itinerario> registrarItinerario(Itinerario itinerario) {
        //PRIMERO VALIDAMOS QUE EL ID NO EXISTA. SI YA EXISTE, DEVOLVERÁ UN OPTIONAL VACIO.
        if (itinerario == null) {
            return Optional.empty();
        }

        //SEGUNDO: VALIDACIÓN DE QUE EL ID NO ESTÉ DUPLICADO.
        if (itinerarioRepository.buscarItinerarioPorId(itinerario.getIdItinerario()).isPresent()) {
            return Optional.empty();
        }

        //COMO LA INTERFAZ DE ENTRADA PIDE UN OBJETO DE LA CLASE Itinerario (es decir: Itinerario guardarItinerario...): Se guardará primero el mét.odo con una variable del tipo itinerario.
        Itinerario itinerarioGuardado = itinerarioRepository.guardarItinerario(itinerario);  //intinerarioRepository porque se lo debemos entregar a la salida.

        //Después, se usa ofNullabel: convierte el resultado en un optional, o uno vacío si es null, PORQUE LA ENTRADA EXIGE UN OPTIONAL, para poder cumplir con el contrato:
        return Optional.ofNullable(itinerarioGuardado);
    }

    //SEGUNDO MÉT.ODO: actualizarItinerario():
    @Override
    public Optional<Itinerario> actualizarItinerario(Itinerario itinerario) {
        //Validamos que itinerario no sea null:
        if (itinerario == null) {
            return Optional.empty();
        }

        //Validamos que el itinerario exista, ya que no tiene sentido actualizar algo que no está registrado:
        if (itinerarioRepository.buscarItinerarioPorId(itinerario.getIdItinerario()).isEmpty()) {
            return Optional.empty();
        }

        //Si pasa estas validaciones, entonces se solicita la actualización. Ahora no hay necesidad de ofNullabel porque ambos métodos de actualizar en ambos puertos ya son Optional:
        return itinerarioRepository.actualizarItinerario(itinerario);
    }

//    public List<Itinerario> mostrarItinerarios(){
//        return itinerarioRepository.itinerariosRegistrados();
//    }
//    public Itinerario buscarId(int idItinerario){
//        return itinerarioRepository.buscarIdItinerarios(idItinerario);
//    }
//
//    public void eliminarItinerario(int idItinerario){
//        itinerarioRepository.eliminarItinerario(idItinerario);
//    }
}
