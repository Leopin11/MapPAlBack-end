package app.service;

import app.domain.EtiquetaInclusiva;
import app.domain.enums.CategoriaEtiqueta;
import app.service.inputport.EtiquetaInclusivaService;
import app.service.outputport.EtiquetaInclusivaRepository;

import java.util.List;
import java.util.Optional;

public class EtiquetaInclusivaServiceImpl implements EtiquetaInclusivaService {

    private final EtiquetaInclusivaRepository etiquetaInclusivaRepository;

    public EtiquetaInclusivaServiceImpl(EtiquetaInclusivaRepository etiquetaInclusivaRepository) {
        this.etiquetaInclusivaRepository = etiquetaInclusivaRepository;
    }


    @Override
    public Optional<EtiquetaInclusiva> registrarEtiqueta(int idEtiqueta, String nombre, CategoriaEtiqueta categoria, String descripcion) {
        if (etiquetaInclusivaRepository.buscarIdEtiquetas(idEtiqueta).isPresent()){
            return Optional.empty();
        }
        EtiquetaInclusiva nuevaEtiqueta = new EtiquetaInclusiva(nombre, idEtiqueta, categoria, descripcion);
        return Optional.of(etiquetaInclusivaRepository.guardarEtiqueta(nuevaEtiqueta));
    }

    @Override
    public Optional<EtiquetaInclusiva> actualizarEtiqueta(int idEtiqueta, String nombre, CategoriaEtiqueta categoria, String descripcion) {
        Optional<EtiquetaInclusiva> existente = etiquetaInclusivaRepository.buscarIdEtiquetas(idEtiqueta);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        EtiquetaInclusiva actualizada = existente.get();
        actualizada.setIdEtiqueta(idEtiqueta);
        actualizada.setNombre(nombre);
        actualizada.setCategoria(categoria);
        actualizada.setDescripcion(descripcion);


        return etiquetaInclusivaRepository.actualizarEtiqueta(idEtiqueta, actualizada);
    }

    @Override
    public List<EtiquetaInclusiva> listarEtiquetas() {
        return etiquetaInclusivaRepository.etiquetasRegistradas();
    }

    @Override
    public Optional<EtiquetaInclusiva> buscarEtiquetasPorId(int idEtiqueta) {
        return etiquetaInclusivaRepository.buscarIdEtiquetas(idEtiqueta);
    }

    @Override
    public boolean eliminarEtiquetaPorId(int idEtiqueta) {
        return etiquetaInclusivaRepository.eliminarEtiqueta(idEtiqueta);
    }
}
