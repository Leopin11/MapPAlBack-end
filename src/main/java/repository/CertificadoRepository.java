package repository;

import domain.Certificado;

import java.util.ArrayList;
import java.util.List;

public class CertificadoRepository {

    private final List<Certificado> certificados;

    public CertificadoRepository(){
        this.certificados = new ArrayList<>();
    }

    public void guardarNuevoCerificado(){
        this.certificados.add(certificado);
    }

    public List<Certificado> certificadosRegistrados(){
        return this.certificados;
    }

    public Certificado buscarCertificadoPorId(Integer idCertificado){
        for (Certificado certificado : this.certificados)
            if (certificado.getIdCertificado().equals(idCertificado)) {
                return certificado;
            }
        return null;
    }

    public void eliminarCertificado(Integer idCertificado){
        this.certificados.removeIf(certificado -> certificado.getIdCertificado().equals(idCertificado));
    }
}
