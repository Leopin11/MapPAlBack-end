package app.persistence.repository;

import app.domain.PrestadorLocal;
import app.service.outputport.PrestadorLocalRepository;

import java.util.List;
import java.util.Optional;

// TEMPORAL: se elimina cuando exista PrestadorLocalRepositoryAdapterDB
public class PrestadorLocalRepositoryImpl implements PrestadorLocalRepository {

    private final List<PrestadorLocal> prestadores;

    public PrestadorLocalRepositoryImpl(List<PrestadorLocal> prestadores) {
        this.prestadores = prestadores;
    }

    @Override
    public PrestadorLocal guardarPrestadorLocal(PrestadorLocal prestadorLocal) {
        prestadores.add(prestadorLocal);
        return prestadorLocal;
    }

    @Override
    public List<PrestadorLocal> PrestadoresRegistrados() {
        return List.copyOf(prestadores);
    }

    @Override
    public Optional<PrestadorLocal> buscarPrestadorPorId(int idPrestador) {
        return prestadores.stream().filter(p -> p.getIdPrestador() == idPrestador).findFirst();

    }

    @Override
    public Optional<PrestadorLocal> actualizarPrestadorLocal(PrestadorLocal prestadorLocal) {
        for (int i = 0; i < prestadores.size(); i++) {
            if (prestadores.get(i).getIdPrestador() == prestadorLocal.getIdPrestador()) {
                prestadores.set(i, prestadorLocal);
                return Optional.of(prestadorLocal);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarPrestadorPorId(int idPrestador) {
        return prestadores.removeIf(p -> p.getIdPrestador() == idPrestador);
    }
}
