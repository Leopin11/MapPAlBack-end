package app.domain.relations;
import app.domain.enums.CategoriaEtiqueta;

public class VehiculoEtiqueta {
    private int idVehiculo;
    private int idEtiqueta;
    private String nombreEtiqueta;
    private CategoriaEtiqueta categoria;

    public VehiculoEtiqueta(){};

    public VehiculoEtiqueta(int idVehiculo, int idEtiqueta, String nombreEtiqueta, CategoriaEtiqueta categoria) {
        this.idVehiculo = idVehiculo;
        this.idEtiqueta = idEtiqueta;
        this.nombreEtiqueta = nombreEtiqueta;
        this.categoria = categoria;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public CategoriaEtiqueta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEtiqueta categoria) {
        this.categoria = categoria;
    }
}
