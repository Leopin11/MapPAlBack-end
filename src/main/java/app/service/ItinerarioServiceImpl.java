package app.service;

import app.domain.Itinerario;
import app.service.inputport.ItinerarioService;
import app.service.outputport.ItinerarioRepository;

import java.util.List;
import java.util.Optional;

public class ItinerarioServiceImpl implements ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;

    public ItinerarioServiceImpl(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }

    @Override
    public Optional<Itinerario> registrarItinerario(Itinerario itinerario) {
        validarItinerario(itinerario);
        if (itinerarioRepository.buscarItinerarioPorId(itinerario.getIdItinerario()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(itinerarioRepository.guardarItinerario(itinerario));
    }

    @Override
    public Optional<Itinerario> actualizarItinerario(Itinerario itinerario) {
        validarItinerario(itinerario);
        return itinerarioRepository.actualizarItinerario(itinerario);
    }

    @Override
    public List<Itinerario> listarItinerarios() {
        return itinerarioRepository.itinerariosRegistrados();
    }

    @Override
    public Optional<Itinerario> buscarItinerarioPorId(int idItinerario) {
        return itinerarioRepository.buscarItinerarioPorId(idItinerario);
    }

    @Override
    public boolean eliminarItinerarioPorId(int idItinerario) {
        return itinerarioRepository.eliminarItinerarioPorId(idItinerario);
    }

    private void validarItinerario(Itinerario itinerario) {
        if (itinerario == null) {
            throw new IllegalArgumentException("El itinerario no puede ser nulo");
        }
        if (itinerario.getDestino() == null || itinerario.getDestino().isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }
        if (itinerario.getFechaDeInicio() == null || itinerario.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }
        if (itinerario.getFechaFin().isBefore(itinerario.getFechaDeInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if (itinerario.getNivelEstimulacion() == null) {
            throw new IllegalArgumentException("El nivel de estimulación es obligatorio");
        }
    }
}