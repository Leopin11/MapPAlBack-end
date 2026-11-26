package app.service;

import app.domain.Comercio;
import app.domain.enums.TipoComercio;
import app.service.inputport.ComercioService;
import app.service.outputport.ComercioRepository;

import java.util.List;
import java.util.Optional;

public class ComercioServicioImpl implements ComercioService {
    private final ComercioRepository comercioRepository;

    public ComercioServicioImpl(ComercioRepository comercioRepository) {
        this.comercioRepository = comercioRepository;
    }

    @Override
    public Optional<Comercio> registrarComercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio) {
        if (comercioRepository.buscarComercioPorId(idComercio).isPresent()) {
            return Optional.empty();
        }
        Comercio nuevoComercio = new Comercio(idComercio,nombreComercio, tipoComercio,direccion,horarioAtencion,tarifaBase,insigniaEspecializada,calificacionPromedio);
        return Optional.of(comercioRepository.guardarComercio(nuevoComercio));
    }

    @Override
    public Optional<Comercio> actualizarComercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio) {
        Optional<Comercio> existente = comercioRepository.buscarComercioPorId(idComercio);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Comercio actualizado = existente.get();
        actualizado.setNombreComercio(nombreComercio);
        actualizado.setTipoComercio(tipoComercio);
        actualizado.setDireccion(direccion);
        actualizado.setHorarioAtencion(horarioAtencion);
        actualizado.setTarifaBase(tarifaBase);
        actualizado.setInsigniaEspecializada(insigniaEspecializada);
        actualizado.setCalificacionPromedio(calificacionPromedio);

        return comercioRepository.actualizarComercio(idComercio,actualizado);
    }

    @Override
    public List<Comercio> Listarcomercios() {
        return comercioRepository.comerciosRegistrados();
    }

    @Override
    public Optional<Comercio> buscarComercioPorId(int idComercio) {
        return comercioRepository.buscarComercioPorId(idComercio);
    }

    @Override
    public boolean eliminarComercioPorId(int idComercio) {
        return comercioRepository.eliminarComercioPorId(idComercio);
    }
}
