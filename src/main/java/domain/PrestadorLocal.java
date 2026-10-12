package domain;

public interface PrestadorLocal {
    public double obtenerTarifaBase();
    public boolean evaluarInsigniaEspecializada();
    public void registrarCertificado(Certificado c);  //Aún no se ha creado Certificado (CARLOS).
}
