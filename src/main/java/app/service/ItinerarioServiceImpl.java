package app.service;

import app.domain.Itinerario;
import app.service.inputport.ItinerarioService;
import app.service.outputport.ItinerarioRepository;

import java.util.List;
import java.util.Optional;

public class ItinerarioServiceImpl implements ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;

    public ItinerarioServiceImpl(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }
    // ...el resto de métodos se queda igual

    public Optional<Itinerario> registrarItinerario(Itinerario itinerario){
        if (itinerario == null){
            System.out.println("El itinerario no puede estar nulo");
            return null;
        }
        System.out.println("Itinerario agregado con existo");
        itinerarioRepository.guardarItinerario(itinerario);
        return null;
    }

    @Override
    public Optional<Itinerario> actualizarItinerario(Itinerario itinerario) {
        return Optional.empty();
    }

    @Override
    public List<Itinerario> listarItinerarios() {
        return List.of();
    }

    @Override
    public Optional<Itinerario> buscarItinerarioPorId(int idItinerario) {
        return Optional.empty();
    }

    @Override
    public boolean eliminarItinerarioPorId(int idItinerario) {
        return false;
    }


}
