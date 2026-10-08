package app.repository;

import app.domain.EtiquetaInclusiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtiquetaInclusivaRepository {

    private final List<EtiquetaInclusiva> etiquetaInclusivas;

    public EtiquetaInclusivaRepository(){
        this.etiquetaInclusivas = new ArrayList<>();
    }

    public void guardarEtiquetaInclusiva(EtiquetaInclusiva etiquetaInclusiva){
        this.etiquetaInclusivas.add(etiquetaInclusiva);
    }

    public List<EtiquetaInclusiva> etiquetasInclusivasRegistradas(){
        return this.etiquetaInclusivas;
    }

    public EtiquetaInclusiva buscarEtiquetaIncllusivaPorId(Integer idEtiquetaInclusiva){
        for (EtiquetaInclusiva etiquetaInclusiva : this.etiquetaInclusivas){
            if (etiquetaInclusiva.getIdEtiqueta().equals(idEtiquetaInclusiva)) {
                return (EtiquetaInclusiva) Collections.singletonList(etiquetaInclusiva);
            }
        }
        return null;
    }
    public void eliminarEtiquetaInclusiva(Integer idEtiquetaInclusiva){
        this.etiquetaInclusivas.removeIf(etiquetaInclusiva -> etiquetaInclusiva.getIdEtiqueta().equals(idEtiquetaInclusiva));
    }
}
