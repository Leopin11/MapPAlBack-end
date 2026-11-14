package app.service.outputport;

import app.domain.PrestadorLocal;
import app.domain.Reserva;

import java.util.List;
import java.util.Optional;

public interface PrestadorLocalRepository {
    public PrestadorLocal guardarPrestadorLocal(PrestadorLocal prestadorLocal);
    public List<PrestadorLocal> PrestadoresRegistrados();
    public Optional<PrestadorLocal> buscarPrestadorPorId(int idPrestador);
    public Optional<PrestadorLocal> actualizarPrestadorLocal(PrestadorLocal prestadorLocal);
    public boolean eliminarPrestadorPorId(int idprestador);
}
