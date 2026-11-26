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

refactor/006/reserva-puertos
    @Override
    public Optional<Itinerario> registrarItinerario(Itinerario itinerario) {
        validarItinerario(itinerario);
        if (itinerarioRepository.buscarItinerarioPorId(itinerario.getIdItinerario()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(itinerarioRepository.guardarItinerario(itinerario));

    //PRIMER MÉT.ODO registrarItinerario():
    @Override
    public Optional<Itinerario> registrarItinerario(Itinerario itinerario) {
        //PRIMERO VALIDAMOS QUE EL ITINERARIO NO SEA NULL. SI LO ES, DEVOLVERÁ UN OPTIONAL VACIO.
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
refactor/006/reserva-puertos
        validarItinerario(itinerario);
        //Validamos que itinerario no sea null:
        if (itinerario == null) {
            return Optional.empty();
        }

        //Si pasa estas validaciones, entonces se solicita la actualización. Ahora no hay necesidad de ofNullabel porque ambos métodos de actualizar en ambos puertos ya son Optional:

        return itinerarioRepository.actualizarItinerario(itinerario);
    }


    //TERCER MÉT.ODO: listarItinierarios():
    @Override
    public List<Itinerario> listarItinerarios() {
 refactor/006/reserva-puertos
        return itinerarioRepository.itinerariosRegistrados();

        return itinerarioRepository.itinerariosRegistrados(); //Llama al mét.odo de la interfaz de salida, recibe la lista de itinerarios y la devuelve a quien llamó a listarItinerarios.

    }


    //CUARTO MÉT.ODO: buscarItinerarioPorId():
    @Override
    public Optional<Itinerario> buscarItinerarioPorId(int idItinerario) {
 refactor/006/reserva-puertos

        //A continuación, devolvemos directamente el Optional recibido del repositorio. Si no encuentra el itinerario, el repositorio debe devolver Optional.empty().
        return itinerarioRepository.buscarItinerarioPorId(idItinerario);
    }


    //QUINTO MÉT.ODO: eliminarItinerarioPorId():
    @Override
    public boolean eliminarItinerarioPorId(int idItinerario) {
        return itinerarioRepository.eliminarItinerarioPorId(idItinerario);
 refactor/006/reserva-puertos
    }

    private void validarItinerario(Itinerario itinerario) {
        if (itinerario == null) {
            throw new IllegalArgumentException("El itinerario no puede ser nulo");
        }
        if (itinerario.getDestino() == null || itinerario.getDestino().isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }
        if (itinerario.getFechaDeInicio() == null || itinerario.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }
        if (itinerario.getFechaFin().isBefore(itinerario.getFechaDeInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if (itinerario.getNivelEstimulacion() == null) {
            throw new IllegalArgumentException("El nivel de estimulación es obligatorio");
        }
    }
}

        //No validaremos que exista primero, ESO LO HAREMOS EN EL REPOSITORY!!
    }
}
