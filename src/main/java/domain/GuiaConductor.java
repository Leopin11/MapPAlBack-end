package domain;

import domain.enums.NivelEstimulacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public static class Itinerario {
        private Integer idItinerario;
        private String destino;
        private LocalDateTime fechaDeInicio;
        private LocalDateTime fechaFin;
        private String tipoExperiencia;
        private NivelEstimulacion nivelEstimulacion;

        public Itinerario(Integer idItinerario, String destino, LocalDateTime fechaDeInicio, LocalDateTime fechaFin, String tipoExperiencia, NivelEstimulacion nivelEstimulacion){
            this.idItinerario = idItinerario;
            this.destino = destino;
            this.fechaDeInicio = fechaDeInicio;
            this.fechaFin = fechaFin;
            this.tipoExperiencia = tipoExperiencia;
            this.nivelEstimulacion = nivelEstimulacion;
        }

        public Integer getIdItinerario() {
            return idItinerario;
        }

        public void setIdItinerario(Integer idItinerario) {
            this.idItinerario = idItinerario;
        }

        public String getDestino() {
            return destino;
        }

        public void setDestino(String destino) {
            this.destino = destino;
        }

        public LocalDateTime getFechaDeInicio() {
            return fechaDeInicio;
        }

        public void setFechaDeInicio(LocalDateTime fechaDeInicio) {
            this.fechaDeInicio = fechaDeInicio;
        }

        public LocalDateTime getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(LocalDateTime fechaFin) {
            this.fechaFin = fechaFin;
        }

        public String getTipoExperiencia() {
            return tipoExperiencia;
        }

        public void setTipoExperiencia(String tipoExperiencia) {
            this.tipoExperiencia = tipoExperiencia;
        }

        public NivelEstimulacion getNivelEstimulacion() {
            return nivelEstimulacion;
        }

        public void setNivelEstimulacion(NivelEstimulacion nivelEstimulacion) {
            this.nivelEstimulacion = nivelEstimulacion;
        }

        public Integer calcularDuracion(){

            return 0;
        }
    }
}