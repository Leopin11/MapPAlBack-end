package app.persistence.repository;

import app.domain.Itinerario;
import app.service.outputport.ItinerarioRepository;

import java.util.*;

public class ItinerarioRepositoryImpl implements ItinerarioRepository {

    private List<Itinerario> itinerarios;

    public ItinerarioRepositoryImpl(){
        this.itinerarios = new ArrayList<>();
    }

    @Override
    public Itinerario guardarItinerario(Itinerario itinerario) {
        this.itinerarios.add(itinerario);
        return itinerario;
    }

    @Override
    public List<Itinerario> itinerariosRegistrados() {
        return Collections.unmodifiableList(itinerarios);
    }

    @Override
    public Optional<Itinerario> buscarItinerarioPorId(int idItinerario) {
        return itinerarios.stream()
                .filter(i -> i.getIdItinerario() == idItinerario)
                .findFirst();
    }

    @Override
    public Optional<Itinerario> actualizarItinerario(Itinerario itinerario) {
        for (int i = 0; i < itinerarios.size(); i++) {
            if (Objects.equals(itinerarios.get(i).getIdItinerario(), itinerario.getIdItinerario())) {
                itinerarios.set(i, itinerario);
                return Optional.of(itinerario);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarItinerarioPorId(int idItinerario) {
        return itinerarios.removeIf(i -> i.getIdItinerario() == idItinerario);
    }
}
