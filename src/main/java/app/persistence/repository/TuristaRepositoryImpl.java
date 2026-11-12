package app.persistence.repository;

import app.domain.Reserva;
import app.domain.Turista;
import app.service.outputport.TuristaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TuristaRepositoryImpl implements TuristaRepository {
    private final List<Turista> turistas;

    public TuristaRepositoryImpl(List<Turista> turistas) {
        this.turistas = turistas;
    }


    @Override
    public Turista guardarTurista(Turista turista) {
        this.turistas.add(turista);
        return turista;
    }

    @Override
    public List<Turista> TuristasRegistrados() {
        return Collections.unmodifiableList(this.turistas);
    }

    @Override
    public Optional<Turista> buscarTuristaPorId(int idTurista) {
        for (Turista turista : this.turistas){
            if (turista.getIdPersona() == idTurista){
                return Optional.of(turista);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Turista> actualizarTurista(Turista turista) {
        for (int i = 0; i < this.turistas.size(); i++) {
            if (this.turistas.get(i).getIdPersona() == turista.getIdPersona()) {
                this.turistas.set(i, turista);
                return Optional.of(turista);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarTuristaPorId(int idTurista) {
        return this.turistas.removeIf(turista -> turista.getIdPersona()==idTurista);
    }
}
