package app.service.outputport;

import app.domain.Comercio;
import java.util.List;
import java.util.Optional;

public interface ComercioRepository {
    Comercio guardarComercio(Comercio comercio);
    List<Comercio> comerciosRegistrados();
    Optional<Comercio> buscarComercioPorId(int idComercio);
    Optional<Comercio> actualizarComercio(int idComercio, Comercio comercio);
    boolean eliminarComercioPorId(int idComercio);
}
