package domain;

import domain.Persona;
import domain.PrestadorLocal;

import java.time.LocalDate;

public class GuiaConductor extends Persona implements PrestadorLocal {
    private double tarifaBase;
    private boolean insigniaEspecializada;
    private double calificacionPromedio;

    //CONSTRUCTOR:
    public GuiaConductor(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio) {
        super(idPersona, nombre, apellido, documentoId, telefono, email, fechaNacimiento, activo);
        this.tarifaBase = tarifaBase;
        this.insigniaEspecializada = insigniaEspecializada;
        this.calificacionPromedio = calificacionPromedio;
    }

    //MÉTODOS DEL CONTRATO (Interfaz "PrestadorLocal"):
    public double obtenerTarifaBase() {
        return this.tarifaBase;
    }

    public boolean evaluarInsigniaEspecializada() {
        return insigniaEspecializada;  //No retorna ninguna lógica todavía.
    }

    @Override
    public void registrarCertificado(Certificado c) {}  //AÚN NO EXISTE "Certificado" (CARLOS).

    //MÉTODOS HEREDADOS:
    @Override
    public int calcularEdad() {return super.calcularEdad();}

    @Override
    public String mostrarResumen() {return "";}


    //LOS MÉTODOS PROPIOS SE DEJARON COMO IMPLEMENTACIONES DE LA INTERFAZ, PUES ESTOS HUBIERAN QUEDADO DUPLICADOS.

    //GETTERS:
    public double getTarifaBase() {return tarifaBase;}

    public boolean isInsigniaEspecializada() {return insigniaEspecializada;}

    public double getCalificacionPromedio() {return calificacionPromedio;}


    //SETTERS:
    public void setTarifaBase(double tarifaBase) {this.tarifaBase = tarifaBase;}

    public void setInsigniaEspecializada(boolean insigniaEspecializada) {this.insigniaEspecializada = insigniaEspecializada;}

    public void setCalificacionPromedio(double calificacionPromedio) {this.calificacionPromedio = calificacionPromedio;}
}