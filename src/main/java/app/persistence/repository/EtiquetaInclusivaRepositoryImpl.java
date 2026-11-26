package app.persistence.repository;

import app.domain.EtiquetaInclusiva;
import app.service.outputport.EtiquetaInclusivaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EtiquetaInclusivaRepositoryImpl implements EtiquetaInclusivaRepository {

   private final List<EtiquetaInclusiva> etiquetaInclusivas;


    public EtiquetaInclusivaRepositoryImpl(List<EtiquetaInclusiva> etiquetaInclusivas) {
        this.etiquetaInclusivas = etiquetaInclusivas;


    }


    @Override
    public EtiquetaInclusiva guardarEtiqueta(EtiquetaInclusiva etiquetaInclusiva) {
        this.etiquetaInclusivas.add(etiquetaInclusiva);
        return etiquetaInclusiva;
    }

    @Override
    public List<EtiquetaInclusiva> etiquetasRegistradas() {
        return Collections.unmodifiableList(etiquetaInclusivas);
    }

    @Override
    public Optional<EtiquetaInclusiva> buscarIdEtiquetas(int idEtiqueta) {
        for (EtiquetaInclusiva  etiquetaInclusiva : this.etiquetaInclusivas){
            if (etiquetaInclusiva.getIdEtiqueta() == idEtiqueta){
                return Optional.of(etiquetaInclusiva);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<EtiquetaInclusiva> actualizarEtiqueta(int idEtiqueta, EtiquetaInclusiva etiquetaInclusiva) {
        for (int i = 0; i < this.etiquetaInclusivas.size(); i++) {
            if (this.etiquetaInclusivas.get(i).getIdEtiqueta() == etiquetaInclusiva.getIdEtiqueta()) {
                this.etiquetaInclusivas.set(i, etiquetaInclusiva);
                return Optional.of(etiquetaInclusiva);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarEtiqueta(int idEtiqueta) {
        return this.etiquetaInclusivas.removeIf(etiquetaInclusiva -> etiquetaInclusiva.getIdEtiqueta()==idEtiqueta);
    }
}
