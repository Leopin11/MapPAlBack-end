package domain;

import domain.enums.CategoriaEtiqueta;

public class EtiquetaInclusiva {
    private int idEtiqueta;
    private String nombre;
    private CategoriaEtiqueta categoria;
    private String descripcion;

    public EtiquetaInclusiva(int idEtiqueta, String nombre, CategoriaEtiqueta categoria, String descripcion){
        this.idEtiqueta = idEtiqueta;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEtiqueta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEtiqueta categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
