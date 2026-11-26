package app.service.outputport;

import app.domain.EtiquetaInclusiva;

import java.util.List;
import java.util.Optional;

public interface EtiquetaInclusivaRepository {
    EtiquetaInclusiva  guardarEtiqueta (EtiquetaInclusiva etiquetaInclusiva);
    List<EtiquetaInclusiva> etiquetasRegistradas();
    Optional<EtiquetaInclusiva> buscarIdEtiquetas(int idEtiqueta);
    Optional<EtiquetaInclusiva> actualizarEtiqueta(int idEtiqueta, EtiquetaInclusiva etiquetaInclusiva);
     boolean eliminarEtiqueta(int idEtiqueta);
}
