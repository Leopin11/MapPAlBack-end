package domain;

import java.time.LocalDate;

public class Certificado {
    private int idCertificado;
    private String nombre;
    private String entidadEmisora;
    private LocalDate fechaObtencion;

    public Certificado(int idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion) {
        this.idCertificado = idCertificado;
        this.nombre = nombre;
        this.entidadEmisora = entidadEmisora;
        this.fechaObtencion = fechaObtencion;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(int idCertificado) {
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
