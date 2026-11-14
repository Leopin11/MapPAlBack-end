package app.service.inputport;

import app.domain.Certificado;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CertificadoService {
    Optional<Certificado> registrarCertificado(int idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion);
    Optional<Certificado> actualizarCertificado(int idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion);
    List<Certificado> listarCertificados();
    Optional<Certificado> buscarCertificadoPorId(int idCertificado);
    boolean eliminarCertificadoPorId(int idCertificado);
}
