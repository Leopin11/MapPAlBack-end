package app.service.outputport;

import app.domain.Itinerario;

import java.util.List;
import java.util.Optional;

public interface ItinerarioRepository {
    Itinerario guardarItinerario(Itinerario itinerario);
    Optional<Itinerario> actualizarItinerario(Itinerario itinerario);
    List<Itinerario> itinerariosRegistrados();
    Optional<Itinerario> buscarItinerarioPorId(int idItinerario);
    boolean eliminarItinerarioPorId(int idItinerario);
}