package repository;

import domain.Certificado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CertificadoRepository {

    private List<Certificado> certificados;

    public CertificadoRepository(){
        this.certificados = new ArrayList<>();
    }

    public void guardarCerificado(Certificado certificado){
        this.certificados = new ArrayList<>();
    }

    public List<Certificado> certificadosRegistrados(){
        return Collections.unmodifiableList(this.certificados);
    }

    public Certificado buscarCertificadoPorId (int idCertificado){
        for (Certificado certificado : this.certificados){
            if (certificado.getIdCertificado() == idCertificado){
                return certificado;
            }
        }
        return null;
    }

    public void eliminarCertificado (int idCertificado){
        this.certificados.removeIf(certificado -> certificado.getIdCertificado() == idCertificado);
    }


}
