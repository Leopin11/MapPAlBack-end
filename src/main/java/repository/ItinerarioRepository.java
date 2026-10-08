package repository;

import domain.GuiaConductor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItinerarioRepository {

    private final List<GuiaConductor.Itinerario> itinerarios;

    public ItinerarioRepository(){
        this.itinerarios = new ArrayList<>();
    }

    public void guardarItinerario (GuiaConductor.Itinerario itinerario){
        this.itinerarios.add(itinerario);
    }

    public List<GuiaConductor.Itinerario> ItinerariosRegistrados(){
        return this.itinerarios;
    }

    public GuiaConductor.Itinerario buscarItinerarioPorId(Integer idItinerario){
        for (GuiaConductor.Itinerario itinerario : this.itinerarios){
            if (itinerario.getIdItinerario().equals(idItinerario)){
                return (GuiaConductor.Itinerario) Collections.singletonList(itinerario);
            }
        }
        return null;
    }
    public void eliminarItinerario(Integer idItinerario){
        this.itinerarios.removeIf(itinerario -> itinerario.getIdItinerario().equals(idItinerario));
    }
}
