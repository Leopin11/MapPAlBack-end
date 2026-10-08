package domain;
import domain.enums.EstadoReserva;
import domain.enums.TipoComercio;
import java.time.LocalDateTime;

public class Comercio implements PrestadorLocal {
    private int idComercio;
    private String nombreComercio;
    private TipoComercio tipoComercio;  //Viene del enum.
    private String direccion;
    private String horarioAtencion;
    private double tarifaBase;
    private boolean insigniaEspecializada;
    private double calificacionPromedio;

    //CONSTRUCTOR:
    public Comercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio) {
        this.idComercio = idComercio;
        this.nombreComercio = nombreComercio;
        this.tipoComercio = tipoComercio;
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
        this.tarifaBase = tarifaBase;
        this.insigniaEspecializada = insigniaEspecializada;
        this.calificacionPromedio = calificacionPromedio;
    }


    //MÉTODOS DEL CONTRATO (Interfaz "PrestadorLocal"):
    @Override
    public double obtenerTarifaBase() {
        return this.tarifaBase;
    }

    @Override
    public boolean evaluarInsigniaEspecializada() {
        return insigniaEspecializada;  //No retorna ninguna lógica todavía.
    }

    @Override
    public void registrarCertificado(Certificado c) {}  // AÚN NO EXISTE "Certificado" (CARLOS).


    //GETTERS:
    public int getIdComercio() {return idComercio;}

    public String getNombreComercio() {return nombreComercio;}

    public TipoComercio getTipoComercio() {return tipoComercio;}

    public String getDireccion() {return direccion;}

    public String getHorarioAtencion() {return horarioAtencion;}

    public double getTarifaBase() {return tarifaBase;}

    public boolean isInsigniaEspecializada() {return insigniaEspecializada;}

    public double getCalificacionPromedio() {return calificacionPromedio;}


    //SETTERS:
    public void setIdComercio(int idComercio) {this.idComercio = idComercio;}

    public void setNombreComercio(String nombreComercio) {this.nombreComercio = nombreComercio;}

    public void setTipoComercio(TipoComercio tipoComercio) {this.tipoComercio = tipoComercio;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public void setHorarioAtencion(String horarioAtencion) {this.horarioAtencion = horarioAtencion;}

    public void setTarifaBase(double tarifaBase) {this.tarifaBase = tarifaBase;}

    public void setInsigniaEspecializada(boolean insigniaEspecializada) {this.insigniaEspecializada = insigniaEspecializada;}

    public void setCalificacionPromedio(double calificacionPromedio) {this.calificacionPromedio = calificacionPromedio;}

    public static class Reserva {

        private Integer idReserva;
        private LocalDateTime fechaReserva;
        private EstadoReserva estado;
        private Double monto;

        private Turista turista;
        private PrestadorLocal prestadorLocal;
        private GuiaConductor.Itinerario itinerario;


        public Reserva(Integer idReserva, LocalDateTime fechaReserva, EstadoReserva estado, Double monto, Turista turista, PrestadorLocal prestadorLocal, GuiaConductor.Itinerario itinerario) {
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

        public GuiaConductor.Itinerario getItinerario() {
            return itinerario;
        }

        public void setItinerario(GuiaConductor.Itinerario itinerario) {
            this.itinerario = itinerario;
        }

        public void confirmar(){

        }
        public void cancelar(){

        }

        public void procesarPagoDirecto(){

        }
    }
}