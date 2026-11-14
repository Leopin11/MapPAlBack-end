package app.service.inputport;

import app.domain.Reserva;
import app.domain.Turista;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TuristaService {
    Optional<Turista> registrarTurista(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, String nacionalidad, String idiomaPreferido);
    Optional<Turista> actualizarTurista(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, String nacionalidad, String idiomaPreferido);
    List<Turista> listarTuristas();
    Optional<Turista> buscarTuristaPorId(int idTurista);
    boolean eliminarTuristaPorId(int idTurista);
}
