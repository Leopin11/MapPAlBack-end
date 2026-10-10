package repository;

import domain.Certificado;

import java.util.ArrayList;
import java.util.List;

public class CertificadoRepository {

    private List<Certificado> certificados;

    public CertificadoRepository(){
        this.certificados = new ArrayList<>();
    }

    public void guardarNuevoCerificado(Certificado certificado){
        this.certificados = new ArrayList<>();
    }

    public List<Certificado> certificadosRegistrados(){
        return this.certificados;
    }


}
