package app.persistence.repository;

import app.domain.Certificado;
import app.domain.Comercio;
import app.service.outputport.ComercioRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ComercioRepositoryImpl implements ComercioRepository {
    private final List<Comercio> comercios;

    public ComercioRepositoryImpl(List<Comercio> comercios) {
        this.comercios = comercios;
    }

    @Override
    public Comercio guardarComercio(Comercio comercio) {
        this.comercios.add(comercio);
        return comercio;
    }

    @Override
    public List<Comercio> comerciosRegistrados() {
        return Collections.unmodifiableList(comercios);
    }

    @Override
    public Optional<Comercio> buscarComercioPorId(int idComercio) {
        for (Comercio comercio : this.comercios){
            if (comercio.getIdComercio() == idComercio){
                return Optional.of(comercio);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Comercio> actualizarComercio(int idComercio, Comercio comercio) {
        for (int i = 0; i < this.comercios.size(); i ++){
            if (this.comercios.get(i).getIdComercio() == comercio.getIdComercio()){
                this.comercios.set(i, comercio);
                return Optional.of(comercio);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarComercioPorId(int idComercio) {
        return this.comercios.removeIf(comercio -> comercio.getIdComercio() == idComercio);
    }
}
