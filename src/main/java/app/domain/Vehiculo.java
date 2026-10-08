package app.domain;

public class Vehiculo {
    private Integer idVehiculo;
    private String placa;
    private String marca;
    private Integer capacidadPasajeros;
    private Boolean baulCapacidadSilla;
    private Boolean rutaSinEscalones;

    public Vehiculo(Integer idVehiculo, String placa, String marca, Integer capacidadPasajeros, Boolean baulCapacidadSilla, Boolean rutaSinEscalones){
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.baulCapacidadSilla = baulCapacidadSilla;
        this.rutaSinEscalones = rutaSinEscalones;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(Integer capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public Boolean getBaulCapacidadSilla() {
        return baulCapacidadSilla;
    }

    public void setBaulCapacidadSilla(Boolean baulCapacidadSilla) {
        this.baulCapacidadSilla = baulCapacidadSilla;
    }

    public Boolean getRutaSinEscalones() {
        return rutaSinEscalones;
    }

    public void setRutaSinEscalones(Boolean rutaSinEscalones) {
        this.rutaSinEscalones = rutaSinEscalones;
    }
    public boolean cumpleFiltro(){

    }

}
