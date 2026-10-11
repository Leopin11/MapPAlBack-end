package repository;

import domain.EtiquetaInclusiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtiquetaInclusivaRepository {

    private List<EtiquetaInclusiva> etiquetaInclusivas;

    public EtiquetaInclusivaRepository(){
        this.etiquetaInclusivas = new ArrayList<>();
    }

    public void guardarEtiqueta(EtiquetaInclusiva etiquetaInclusiva){
        this.etiquetaInclusivas = new ArrayList<>();
    }

    public List<EtiquetaInclusiva> etiquetasRegistradas(){
        return Collections.unmodifiableList(etiquetaInclusivas);
    }

    public EtiquetaInclusiva buscarIdEtiquetas(int idEtiqueta){
        for (EtiquetaInclusiva etiquetaInclusiva : this.etiquetaInclusivas){
            if (etiquetaInclusiva.getIdEtiqueta() == idEtiqueta){
                return etiquetaInclusiva;
            }
        }
        return null;
    }

    public void eliminarEtiqueta(int idEtiqueta){
        this.etiquetasRegistradas().removeIf(etiquetaInclusiva -> etiquetaInclusiva.getIdEtiqueta() == idEtiqueta);
    }


}
