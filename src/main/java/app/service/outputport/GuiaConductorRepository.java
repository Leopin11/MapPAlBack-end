package app.service.outputport;

import app.domain.GuiaConductor;
import java.util.List;
import java.util.Optional;

public interface GuiaConductorRepository {
    GuiaConductor guardarGuiaConductor(GuiaConductor guia);
    Optional<GuiaConductor> buscarPorId(int idPersona);
    List<GuiaConductor> listarTodos();
    Optional<GuiaConductor> actualizarGuiaConductor(GuiaConductor guia);
    boolean eliminarGuiaConductor(int idPersona);
}
