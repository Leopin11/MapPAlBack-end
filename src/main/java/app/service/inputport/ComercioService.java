package app.service.inputport;

import app.domain.Certificado;
import app.domain.Comercio;
import app.domain.enums.TipoComercio;

import java.util.List;
import java.util.Optional;

public interface ComercioService {
    Optional<Comercio> registrarComercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio);
    Optional<Comercio> actualizarComercio(int idComercio, String nombreComercio, TipoComercio tipoComercio, String direccion, String horarioAtencion, double tarifaBase, boolean insigniaEspecializada, double calificacionPromedio);
    List<Comercio> Listarcomercios();
    Optional<Comercio> buscarComercioPorId(int idComercio);
    boolean eliminarComercioPorId(int idComercio);

}
