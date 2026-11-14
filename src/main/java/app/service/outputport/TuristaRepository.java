package app.service.outputport;

import app.domain.Reserva;
import app.domain.Turista;

import java.util.List;
import java.util.Optional;

public interface TuristaRepository {
    public Turista guardarTurista(Turista turista);
    public List<Turista> TuristasRegistrados();
    public Optional<Turista> buscarTuristaPorId(int idTurista);
    public Optional<Turista> actualizarTurista(Turista turista);
    public boolean eliminarTuristaPorId(int idTurista);
}
