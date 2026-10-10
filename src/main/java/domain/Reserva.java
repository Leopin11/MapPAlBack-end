package domain;

import domain.enums.EstadoReserva;

import java.time.LocalDateTime;

public class Reserva {
    private int idReserva;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private double montoTotal;
    private Turista turista;
    private PrestadorLocal prestadorLocal;
    private Itinerario itinerario;

    public Reserva(int idReserva, LocalDateTime fechaReserva, EstadoReserva estado, double monto, Turista turista, PrestadorLocal prestadorLocal, Itinerario itinerario) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.montoTotal = monto;
        this.turista = turista;
        this.prestadorLocal = prestadorLocal;
        this.itinerario = itinerario;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
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

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
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
}
