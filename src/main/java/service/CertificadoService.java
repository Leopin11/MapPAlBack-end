package service;

import domain.Persona;

import java.time.LocalDate;

public class CertificadoService {


    public static class Turista extends Persona {
        private String nacionalidad;
        private String idiomaPreferido;

        //CONTRUCTOR:
        public Turista(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo, String nacionalidad, String idiomaPreferido) {
            super(idPersona, nombre, apellido, documentoId, telefono, email, fechaNacimiento, activo);
            this.nacionalidad = nacionalidad;
            this.idiomaPreferido = idiomaPreferido;
        }

        //MÉTODOS HEREDADOS:
        @Override
        public int calcularEdad() {
            return super.calcularEdad();
        }

        @Override
        public String mostrarResumen(){return "";}  //Se queda VACÍO por ahora.

        //MÉTODOS PROPIOS:
        public void armarItinerario(String destino, LocalDate fechas, String tipo) {}  //AÚN NO EXISTE "Itinerario". Por eso está como VOID (CARLOS).
        public void configurarFiltrosDeAccesibilidad(EtiquetaInclusiva etiqueta) {}  //AÚN NO EXISTE "EtiquetaInclusiva" (CARLOS).

        //GETTERS:
        public String getNacionalidad() {return nacionalidad;}

        public String getIdiomaPreferido() {return idiomaPreferido;}

        //SETTERS:
        public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}

        public void setIdiomaPreferido(String idiomaPreferido) {this.idiomaPreferido = idiomaPreferido;}
    }
}
