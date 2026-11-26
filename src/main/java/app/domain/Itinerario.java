package app.domain;

import app.domain.enums.NivelEstimulacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Itinerario {
    private int idItinerario;
    private String destino;
    private LocalDate fechaDeInicio;
    private LocalDate fechaFin;
    private String tipoExperiencia;
    private NivelEstimulacion nivelEstimulacion;

    public Itinerario(int idItinerario, String destino, LocalDate fechaDeInicio, LocalDate fechaFin, String tipoExperiencia, NivelEstimulacion nivelEstimulacion){
        this.idItinerario = idItinerario;
        this.destino = destino;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaFin = fechaFin;
        this.tipoExperiencia = tipoExperiencia;
        this.nivelEstimulacion = nivelEstimulacion;
    }

    public Itinerario() {

    }

    public Integer getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaDeInicio() {
        return this.fechaDeInicio;
    }

    public void setFechaDeInicio(LocalDate fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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

    public int calcularDuracion(){

        return 0;
    }
}
