package app.service;

import app.domain.GuiaConductor;
import app.service.inputport.GuiaConductorService;
import app.service.outputport.GuiaConductorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GuiaConductorServicioImpl implements GuiaConductorService {

    private static final int EDAD_MINIMA = 18;
    private static final double CALIFICACION_MIN = 0.0;
    private static final double CALIFICACION_MAX = 5.0;
    private static final double CALIFICACION_PARA_INSIGNIA = 4.5; // regla de ejemplo, ajústala a tu negocio

    private final GuiaConductorRepository guiaConductorRepository;

    public GuiaConductorServicioImpl(GuiaConductorRepository guiaConductorRepository) {
        this.guiaConductorRepository = guiaConductorRepository;
    }


    @Override
    public Optional<GuiaConductor> registrarGuiaConductor(GuiaConductor guia, int idPersona) {
        if (guiaConductorRepository.buscarPorId(guia.getIdPersona()).isPresent()) {
            return Optional.empty();
        }
        GuiaConductor nuevoGuia = new GuiaConductor(guia.getIdPersona(), guia.getNombre(), guia.getApellido(),
                guia.getDocumentoId(), guia.getTelefono(), guia.getEmail(),
                guia.getFechaNacimiento(), guia.isActivo(), guia.getTarifaBase(),
                guia.isInsigniaEspecializada(), guia.getCalificacionPromedio());
        return Optional.of(guiaConductorRepository.guardarGuiaConductor(nuevoGuia));
    }

    @Override
    public Optional<GuiaConductor> buscarGuiaConductor(int idPersona) {
        return guiaConductorRepository.buscarPorId(idPersona);
    }

    @Override
    public List<GuiaConductor> listarGuiasConductores() {
        return guiaConductorRepository.listarTodos();
    }

    @Override
    public Optional<GuiaConductor> actualizarTarifaBase(int idPersona, double nuevaTarifa) {
        Optional<GuiaConductor> existente = guiaConductorRepository.buscarPorId(idPersona);
        if (existente.isEmpty()){
            return Optional.empty();
        }
        GuiaConductor actualizado = existente.get();
        actualizado.setTarifaBase(nuevaTarifa);


        return guiaConductorRepository.actualizarGuiaConductor(actualizado);
    }

    @Override
    public GuiaConductor registrarCalificacion(int idPersona, double calificacion) {
        return null;
    }

    @Override
    public GuiaConductor otorgarInsignia(int idPersona) {
        return null;
    }

    @Override
    public boolean eliminarGuiaConductor(int idPersona) {

        return guiaConductorRepository.eliminarGuiaConductor(idPersona);
    }

    // ---------- Validaciones privadas ----------

    private void validarDatosPersonales(GuiaConductor guia) {
        if (guia == null) {
            throw new IllegalArgumentException("El guía no puede ser nulo");
        }
        if (esVacio(guia.getNombre())) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (esVacio(guia.getApellido())) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (esVacio(guia.getDocumentoId())) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (guia.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
        if (guia.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        if (guia.calcularEdad() < EDAD_MINIMA) {
            throw new IllegalArgumentException("El guía debe ser mayor de " + EDAD_MINIMA + " años");
        }
    }


    private void validarTarifa(double tarifa) {
        if (tarifa < 0) {
            throw new IllegalArgumentException("La tarifa base no puede ser negativa");
        }
    }

    private void validarCalificacion(double calificacion) {
        if (calificacion < CALIFICACION_MIN || calificacion > CALIFICACION_MAX) {
            throw new IllegalArgumentException(
                    "La calificación debe estar entre " + CALIFICACION_MIN + " y " + CALIFICACION_MAX);
        }
    }

    private boolean esVacio(String texto) {
        return texto == null || texto.isBlank();
    }


}
