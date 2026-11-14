package app.service;

import app.domain.PrestadorLocal;
import app.service.inputport.PrestadorLocalService;
import app.service.outputport.PrestadorLocalRepository;

import java.util.List;
import java.util.Optional;

public class PrestadorLocalServiceImpl implements PrestadorLocalService {

    private final PrestadorLocalRepository prestadorLocalRepository;

    public PrestadorLocalServiceImpl(PrestadorLocalRepository prestadorLocalRepository) {
        this.prestadorLocalRepository = prestadorLocalRepository;
    }

    @Override
    public Optional<PrestadorLocal> registrarPrestador(PrestadorLocal prestadorLocal) {
        if (prestadorLocalRepository.buscarPrestadorPorId(prestadorLocal.getIdPrestador()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(prestadorLocalRepository.guardarPrestadorLocal(prestadorLocal));
    }

    @Override
    public Optional<PrestadorLocal> buscarPrestadorPorId(int idPersona) {
        return prestadorLocalRepository.buscarPrestadorPorId(idPersona);
    }

    @Override
    public List<PrestadorLocal> listarPrestadores() {
        return prestadorLocalRepository.PrestadoresRegistrados();
    }

    @Override
    public boolean eliminarPrestador(int idPersona) {
        return prestadorLocalRepository.eliminarPrestadorPorId(idPersona);
    }
}
