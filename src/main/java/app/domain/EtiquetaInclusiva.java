package app.domain;

import app.domain.enums.CategoriaEtiqueta;

public class EtiquetaInclusiva {
    private int idEtiqueta;
    private String nombre;
    private CategoriaEtiqueta categoria;
    private String descripcion;

    public EtiquetaInclusiva(String nombre, int idEtiqueta, CategoriaEtiqueta categoria, String descripcion) {
        this.nombre = nombre;
        this.idEtiqueta = idEtiqueta;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public EtiquetaInclusiva() {

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

    public String getCategoria() {
        return null;
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
