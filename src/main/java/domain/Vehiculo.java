package domain;

public class Vehiculo {
    private int idVehiculo;
    private String placa;
    private String marca;
    private int capacidadPasajeros;
    private boolean baulCapacidadSilla;
    private boolean rutaSinEscalones;

    public Vehiculo(int idVehiculo, String placa, String marca, int capacidadPasajeros, boolean baulCapacidadSilla, boolean rutaSinEscalones) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.baulCapacidadSilla = baulCapacidadSilla;
        this.rutaSinEscalones = rutaSinEscalones;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
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

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public boolean isBaulCapacidadSilla() {
        return baulCapacidadSilla;
    }

    public void setBaulCapacidadSilla(boolean baulCapacidadSilla) {
        this.baulCapacidadSilla = baulCapacidadSilla;
    }

    public boolean isRutaSinEscalones() {
        return rutaSinEscalones;
    }

    public void setRutaSinEscalones(boolean rutaSinEscalones) {
        this.rutaSinEscalones = rutaSinEscalones;
    }
}
