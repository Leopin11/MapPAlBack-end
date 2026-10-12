package service;

import domain.EtiquetaInclusiva;
import repository.CertificadoRepository;
import repository.EtiquetaInclusivaRepository;

import java.util.List;

public class EtiquetaInclusivaService {

    private final EtiquetaInclusivaRepository etiquetaInclusivaRepository;


    public EtiquetaInclusivaService(EtiquetaInclusivaRepository etiquetaInclusivaRepository) {
        this.etiquetaInclusivaRepository = etiquetaInclusivaRepository;
    }

    public void registrarEtiqueta(EtiquetaInclusiva etiquetaInclusiva){
        if (etiquetaInclusiva == null){
            System.out.println("La etiqueta no puede estar nula");
            return;
        }
        System.out.println("La Etiqueta ha sido guardada con existo");
        etiquetaInclusivaRepository.guardarEtiqueta(etiquetaInclusiva);
    }
    public List<EtiquetaInclusiva> mostrarEtiquetas(){
        return etiquetaInclusivaRepository.etiquetasRegistradas();
    }

    public EtiquetaInclusiva buscarId(int idEtiqueta){
        return etiquetaInclusivaRepository.buscarIdEtiquetas(idEtiqueta);
    }

    public void eliminarEtiqueta(int idEtiqueta){
        etiquetaInclusivaRepository.eliminarEtiqueta(idEtiqueta);
    }

}
