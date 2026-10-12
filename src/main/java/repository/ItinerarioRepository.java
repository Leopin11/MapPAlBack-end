package repository;

import domain.Itinerario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ItinerarioRepository {

    private List<Itinerario> itinerarios;

    public ItinerarioRepository(){
        this.itinerarios = new ArrayList<>();
    }

    public void guardarItinerario(Itinerario itinerario){
        this.itinerarios = new ArrayList<>();
    }

    public List<Itinerario> itinerariosRegistrados(){
        return Collections.unmodifiableList(itinerarios);
    }

    public Itinerario buscarIdItinerarios(int idItinerario){
        for (Itinerario itinerario : this.itinerarios){
            if (itinerario.getIdItinerario() == idItinerario){
                return itinerario;
            }
        }
        return null;
    }
    public void eliminarItinerario(int idItinerario){
        this.itinerarios.removeIf(itinerario -> itinerario.getIdItinerario() == idItinerario);
    }
}
