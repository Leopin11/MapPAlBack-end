package app.repository;

import app.domain.Certificado;
import app.service.outputport.CertificadoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CertificadoRepositoryImpl implements CertificadoRepository {

    private List<Certificado> certificados;

    public CertificadoRepositoryImpl(){
        this.certificados = new ArrayList<>();
    }

    public Certificado guardarCertificado(Certificado certificado){
        this.certificados.add(certificado);
        return certificado;
    }

    public List<Certificado> certificadosRegistrados(){
        return Collections.unmodifiableList(this.certificados);
    }

    public Optional<Certificado> buscarCertificadoPorId (int idCertificado){
        for (Certificado certificado : this.certificados){
            if (certificado.getIdCertificado() == idCertificado){
                return Optional.of(certificado);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Certificado> actualizarCertificado(Certificado certificado) {
        for (int i = 0; i < this.certificados.size(); i++) {
            if (this.certificados.get(i).getIdCertificado() == certificado.getIdCertificado()) {
                this.certificados.set(i, certificado);
                return Optional.of(certificado);
            }
        }
        return Optional.empty();
    }

    public boolean eliminarCertificadoPorId (int idCertificado){
        return this.certificados.removeIf(certificado -> certificado.getIdCertificado() == idCertificado);
    }


}
