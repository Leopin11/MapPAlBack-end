package domain;

import java.time.LocalDate;

public abstract class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private String documentoId;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private boolean activo;

    //CONSTRUCTOR:
    public Persona(int idPersona, String nombre, String apellido, String documentoId, String telefono, String email, LocalDate fechaNacimiento, boolean activo) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoId = documentoId;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
    }

    //MÉTODOS:
    public int calcularEdad() {
        return 0;
    }

    public abstract String mostrarResumen();


    //GETTERS:
    public int getIdPersona() {return idPersona;}

    public String getNombre() {return nombre;}

    public String getApellido() {return apellido;}

    public String getDocumentoId() {return documentoId;}

    public String getTelefono() {return telefono;}

    public String getEmail() {return email;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public boolean isActivo() {return activo;}


    //SETTERS:
    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public void setDocumentoId(String documentoId) {this.documentoId = documentoId;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setEmail(String email) {this.email = email;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setActivo(boolean activo) {this.activo = activo;}

    public void setIdPersona(int idPersona) {this.idPersona = idPersona;}
}