package domain;

import domain.PrestadorLocal;
import enums.TipoComercio;

public class Comercio implements PrestadorLocal {
    private int idComercio;
    private String nombreComercio;
    private TipoComercio tipoComercio;  //Viene del enum.
    private String direccion;
    private String horarioAtencion;
    private double tarifaBase;
    private boolean insigniaEspecializada;
    private double calificacionPromedio;

    //CONSTRUCTOR:
    public Comercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio) {
        this.idComercio = idComercio;
        this.nombreComercio = nombreComercio;
        this.tipoComercio = tipoComercio;
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
        this.tarifaBase = tarifaBase;
        this.insigniaEspecializada = insigniaEspecializada;
        this.calificacionPromedio = calificacionPromedio;
    }


    //MÉTODOS DEL CONTRATO (Interfaz "PrestadorLocal"):
    @Override
    public double obtenerTarifaBase() {
        return this.tarifaBase;
    }

    @Override
    public boolean evaluarInsigniaEspecializada() {
        return insigniaEspecializada;  //No retorna ninguna lógica todavía.
    }

    @Override
    public void registrarCertificado(Certificado c) {}  // AÚN NO EXISTE "Certificado" (CARLOS).


    //GETTERS:
    public int getIdComercio() {return idComercio;}

    public String getNombreComercio() {return nombreComercio;}

    public TipoComercio getTipoComercio() {return tipoComercio;}

    public String getDireccion() {return direccion;}

    public String getHorarioAtencion() {return horarioAtencion;}

    public double getTarifaBase() {return tarifaBase;}

    public boolean isInsigniaEspecializada() {return insigniaEspecializada;}

    public double getCalificacionPromedio() {return calificacionPromedio;}


    //SETTERS:
    public void setIdComercio(int idComercio) {this.idComercio = idComercio;}

    public void setNombreComercio(String nombreComercio) {this.nombreComercio = nombreComercio;}

    public void setTipoComercio(TipoComercio tipoComercio) {this.tipoComercio = tipoComercio;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public void setHorarioAtencion(String horarioAtencion) {this.horarioAtencion = horarioAtencion;}

    public void setTarifaBase(double tarifaBase) {this.tarifaBase = tarifaBase;}

    public void setInsigniaEspecializada(boolean insigniaEspecializada) {this.insigniaEspecializada = insigniaEspecializada;}

    public void setCalificacionPromedio(double calificacionPromedio) {this.calificacionPromedio = calificacionPromedio;}
}