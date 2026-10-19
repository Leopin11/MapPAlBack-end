package app.service;

import app.domain.Certificado;
import app.repository.CertificadoRepository;

import java.util.List;

public class CertificadoService {

    private final CertificadoRepository certificadoRepository;


    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public void registrarCertificado(Certificado certificado){
        if (certificado == null){
            System.out.println("El certificado no puede ser nulo");
            return;
        }
        System.out.println("Certificado registrado con exito");
        certificadoRepository.guardarCerificado(certificado);
    }

    public List<Certificado> mostrarCertificados (){
        return certificadoRepository.certificadosRegistrados();
    }

    public Certificado bucarIdCertificado (int idCertificado){
        return certificadoRepository.buscarCertificadoPorId(idCertificado);
    }

    public void eliminarCertificado (int idCertificado){
        certificadoRepository.eliminarCertificado(idCertificado);
    }
}
