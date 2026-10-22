package app.service;


import app.domain.Certificado;
import app.service.inputport.CertificadoService;
import app.service.outputport.CertificadoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public CertificadoServiceImpl(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    @Override
    public Optional<Certificado> registrarCertificado(int idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion) {
        // Se valida que el id no se repita
        if(certificadoRepository.buscarCertificadoPorId(idCertificado).isPresent()) {
            return Optional.empty();
        }
        //Se instnacia el nuevo certificado
        Certificado nuevoCertificado = new Certificado(idCertificado, nombre, entidadEmisora,fechaObtencion);
        return Optional.of(certificadoRepository.guardarCertificado(nuevoCertificado));
    }

    @Override
    public Optional<Certificado> actualizarCertificado(int idCertificado, String nombre, String entidadEmisora, LocalDate fechaObtencion) {
        Optional<Certificado>  existente = certificadoRepository.buscarCertificadoPorId(idCertificado);
        if(existente.isEmpty()){
            return Optional.empty();
        }

        Certificado actualizado = existente.get();
        actualizado.setNombre(nombre);
        actualizado.setEntidadEmisora(entidadEmisora);
        actualizado.setFechaObtencion(fechaObtencion);

        return certificadoRepository.actualizarCertificado(actualizado);
    }

    @Override
    public List<Certificado> listarCertificados() {
        return certificadoRepository.certificadosRegistrados();
    }

    @Override
    public Optional<Certificado> buscarCertificadoPorId(int idCertificado) {
        return certificadoRepository.buscarCertificadoPorId(idCertificado);
    }

    @Override
    public boolean eliminarCertificadoPorId(int idCertificado) {
        return certificadoRepository.eliminarCertificadoPorId(idCertificado);
    }
}
