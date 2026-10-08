package domain;

import domain.enums.EstadoReserva;

import java.time.LocalDateTime;

public class Reserva {

    private Integer idReserva;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private Double monto;

    private Turista turista;
    private PrestadorLocal prestadorLocal;
    private Itinerario itinerario;


    public Reserva(Integer idReserva, LocalDateTime fechaReserva, EstadoReserva estado, Double monto, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.monto = monto;
        this.turista = turista;
        this.prestadorLocal = prestadorLocal;
        this.itinerario = itinerario;
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

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public PrestadorLocal getPrestadorLocal() {
        return prestadorLocal;
    }

    public void setPrestadorLocal(PrestadorLocal prestadorLocal) {
        this.prestadorLocal = prestadorLocal;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public void confirmar() {

    }

    public void cancelar() {

    }

    public void procesarPagoDirecto() {

    }
}
