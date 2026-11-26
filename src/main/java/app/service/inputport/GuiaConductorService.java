package app.service.inputport;

import app.domain.GuiaConductor;

import java.util.List;
import java.util.Optional;

public interface GuiaConductorService {

    Optional<GuiaConductor> registrarGuiaConductor(GuiaConductor guia, int idPersona);
    Optional<GuiaConductor> buscarGuiaConductor(int idPersona);
    List<GuiaConductor> listarGuiasConductores();
    Optional<GuiaConductor> actualizarTarifaBase(int idPersona, double nuevaTarifa);
    GuiaConductor registrarCalificacion(int idPersona, double calificacion);
    GuiaConductor otorgarInsignia(int idPersona);
    boolean eliminarGuiaConductor(int idPersona);
}
