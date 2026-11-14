package app.service.inputport;

import app.domain.PrestadorLocal;

import java.util.List;
import java.util.Optional;

public interface PrestadorLocalService {
    Optional<PrestadorLocal> registrarPrestador(
            PrestadorLocal prestadorLocal
    );

    Optional<PrestadorLocal> buscarPrestadorPorId(
            int idPersona
    );

    List<PrestadorLocal> listarPrestadores();

    boolean eliminarPrestador(
            int idPersona
    );
}
