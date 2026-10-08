package app.domain;

import app.domain.enums.EstadoReserva;

import java.time.LocalDateTime;

public class Reserva {
    private Integer idReserva;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private Double monto;

    public Reserva(Integer idReserva, LocalDateTime fechaReserva, EstadoReserva estado, Double monto){
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.monto = monto;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void confirmar(){

    }
    public void cancelar(){

    }

    public void procesarPagoDirecto(){

    }
}
