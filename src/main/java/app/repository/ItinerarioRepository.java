package app.repository;

import app.domain.Itinerario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItinerarioRepository {

    private final List<Itinerario> itinerarios;

    public ItinerarioRepository(){
        this.itinerarios = new ArrayList<>();
    }

    public void guardarItinerario (Itinerario itinerario){
        this.itinerarios.add(itinerario);
    }

    public List<Itinerario> ItinerariosRegistrados(){
        return this.itinerarios;
    }

    public Itinerario buscarItinerarioPorId(Integer idItinerario){
        for (Itinerario itinerario : this.itinerarios){
            if (itinerario.getIdItinerario().equals(idItinerario)){
                return (Itinerario) Collections.singletonList(itinerario);
            }
        }
        return null;
    }
    public void eliminarItinerario(Integer idItinerario){
        this.itinerarios.removeIf(itinerario -> itinerario.getIdItinerario().equals(idItinerario));
    }
}
