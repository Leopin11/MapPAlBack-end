package app.service;

import app.domain.Itinerario;
import app.repository.ItinerarioRepository;

import java.util.List;

public class ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;

    public ItinerarioService(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }

    public void registrarItinerario(Itinerario itinerario){
        if (itinerario == null){
            System.out.println("El itinerario no puede estar nulo");
            return;
        }
        System.out.println("Itinerario agregado con existo");
        itinerarioRepository.guardarItinerario(itinerario);
    }

    public List<Itinerario> mostrarItinerarios(){
        return itinerarioRepository.itinerariosRegistrados();
    }
    public Itinerario buscarId(int idItinerario){
        return itinerarioRepository.buscarIdItinerarios(idItinerario);
    }

    public void eliminarItinerario(int idItinerario){
        itinerarioRepository.eliminarItinerario(idItinerario);
    }
}
