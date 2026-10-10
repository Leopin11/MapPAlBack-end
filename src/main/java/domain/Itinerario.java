package domain;

import domain.enums.NivelEstimulacion;

import java.time.LocalDateTime;

public class Itinerario {
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
