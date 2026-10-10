package domain;

import java.time.LocalDate;

public class Certificado {
    private Integer idCertificado;
    private String nombre;
    private String entidadEmisora;
    private LocalDate fechaObtencion;

    public Certificado(Integer idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion){
        this.idCertificado = idCertificado;
        this.nombre = nombre;
        this.entidadEmisora = entidadEmisora;
        this.fechaObtencion = fechaObtencion;
    }


    public Integer getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntidadEmisora() {
        return entidadEmisora;
    }

    public void setEntidadEmisora(String entidadEmisora) {
        this.entidadEmisora = entidadEmisora;
    }

    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    public void setFechaObtencion(LocalDate fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }


}
