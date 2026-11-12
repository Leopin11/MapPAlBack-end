package app.service.inputport;

import app.domain.Itinerario;

import java.util.List;
import java.util.Optional;

public interface ItinerarioService {
    Optional<Itinerario> registrarItinerario(Itinerario itinerario);
    Optional<Itinerario> actualizarItinerario(Itinerario itinerario);
    List<Itinerario> listarItinerarios();
    Optional<Itinerario> buscarItinerarioPorId(int idItinerario);
    boolean eliminarItinerarioPorId(int idItinerario);
}
