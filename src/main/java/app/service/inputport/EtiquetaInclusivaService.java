package app.service.inputport;

import app.domain.EtiquetaInclusiva;
import app.domain.enums.CategoriaEtiqueta;

import java.util.List;
import java.util.Optional;

public interface EtiquetaInclusivaService {
    Optional<EtiquetaInclusiva> registrarEtiqueta(int idEtiqueta, String nombre, CategoriaEtiqueta categoria, String descripcion);
    Optional<EtiquetaInclusiva> actualizarEtiqueta(int idEtiqueta, String nombre, CategoriaEtiqueta categoria, String descripcion);
    List<EtiquetaInclusiva> listarEtiquetas();
    Optional<EtiquetaInclusiva> buscarEtiquetasPorId(int idEtiqueta);
    boolean eliminarEtiquetaPorId(int idEtiqueta);
}
