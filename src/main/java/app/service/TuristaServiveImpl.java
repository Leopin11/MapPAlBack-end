package app.service;

import app.domain.Turista;
import app.service.inputport.TuristaService;
import app.service.outputport.TuristaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TuristaServiveImpl implements TuristaService {

    private final TuristaRepository turistaRepository;

    public TuristaServiveImpl(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
    }

    @Override
    public Optional<Turista> registrarTurista(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, String nacionalidad, String idiomaPreferido) {
        if (turistaRepository.buscarTuristaPorId(idPersona).isPresent()){
            return Optional.empty();
        }
        Turista nuevoTurista = new Turista(idPersona, nombre, apellido, documentoId, telefono, email, fechaNacimiento, activo, nacionalidad, idiomaPreferido);
        return Optional.of(turistaRepository.guardarTurista(nuevoTurista));
    }

    @Override
    public Optional<Turista> actualizarTurista(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, String nacionalidad, String idiomaPreferido) {
        Optional<Turista> existente = turistaRepository.buscarTuristaPorId(idPersona);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Turista actualizado = existente.get();
        actualizado.setIdPersona(idPersona);
        actualizado.setNombre(nombre);
        actualizado.setApellido(apellido);
        actualizado.setDocumentoId();
        actualizado.setTelefono(telefono);
        actualizado.setEmail(email);
        actualizado.setFechaNacimiento(fechaNacimiento);
        actualizado.setActivo(activo);
        actualizado.setNacionalidad(nacionalidad);
        actualizado.setIdiomaPreferido(idiomaPreferido);

        return turistaRepository.actualizarTurista(actualizado);
    }

    @Override
    public List<Turista> listarTuristas() {
        return turistaRepository.TuristasRegistrados();
    }

    @Override
    public Optional<Turista> buscarTuristaPorId(int idTurista) {
        return turistaRepository.buscarTuristaPorId(idTurista);
    }

    @Override
    public boolean eliminarTuristaPorId(int idTurista) {
        return turistaRepository.eliminarTuristaPorId(idTurista);
    }
}
