package app.service.outputport;

import app.domain.Certificado;

import java.util.List;
import java.util.Optional;

public interface CertificadoRepository {
    Certificado guardarCertificado(Certificado certificado);
    List<Certificado> certificadosRegistrados();
    Optional<Certificado> buscarCertificadoPorId(int idCertificado);
    Optional<Certificado> actualizarCertificado(Certificado certificado);
    boolean eliminarCertificadoPorId(int idCertificado);
}
