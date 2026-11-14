package app.view;

import app.domain.Certificado;
import app.service.inputport.CertificadoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CertificadoView {
    private final CertificadoService certificadoService;
    private final Consola consola;


    public CertificadoView(CertificadoService certificadoService, Consola consola) {
        this.certificadoService = certificadoService;
        this.consola = consola;
    }

    private void guardar() {
        System.out.println("====== Datos del Certificado =====");
        int idCertificado = consola.leerEntero("Ingrese el id del Certificado:");
        String nombre = consola.leerTexto("Ingrese el nombre del Certificado:");
        String entidadEmisora = consola.leerTexto("Ingrese la entidad que emitio el Certificado");
        LocalDate fechaObtencion = consola.leerFecha("Ingrese la fecha en que obtuvo el Certificado ");

        Optional<Certificado> registrado = certificadoService.registrarCertificado(idCertificado, nombre, entidadEmisora, fechaObtencion);

        if (registrado.isPresent()) {
            System.out.println("Certificado registrado con éxito");
        } else {
            System.out.println("No se pudo registrar: ya existe un Certificado con ID: " + idCertificado);
        }
    }

    private void listar() {
        System.out.println("===== Lista de Certificados =====");
        List<Certificado> lista = certificadoService.listarCertificados();

        if (lista.isEmpty()) {
            System.out.println("No hay Certificados Registrados!");
        } else {
            for (Certificado certificado : lista) {
                System.out.println("ID: " + certificado.getIdCertificado() +
                        "\n Nombre: " + certificado.getNombre() +
                        "\n Entidad Emisora: " + certificado.getEntidadEmisora() +
                        "\n Fecha de Obtencion: " + certificado.getFechaObtencion()
                );
            }
        }
    }

    private void buscarId() {
        System.out.println("====== Buscar Certificado por ID =====");

        int id = consola.leerEntero("Ingrese el id del Certificado que desea Buscar: ");

        Optional<Certificado> encontrado = certificadoService.buscarCertificadoPorId(id);
        if (encontrado.isPresent()) {
            Certificado certificado = encontrado.get();
            System.out.println("Se encontró el Certificado:");
            System.out.println("ID: " + certificado.getIdCertificado() +
                    "\n Nombre: " + certificado.getNombre() +
                    "\n Entidad Emisora: " + certificado.getEntidadEmisora() +
                    "\n Fecha de Obtencion: " + certificado.getFechaObtencion()
            );
        } else {
            System.out.println("No se encontró el Certificado con el ID: " + id);
        }
    }

    private void actualizar() {
        System.out.println("======= Actualizar Certificado =======");

        int id = consola.leerEntero("Ingrese el id del Certificado a actualizar:");

        Optional<Certificado> existente = certificadoService.buscarCertificadoPorId(id);
        if (existente.isEmpty()) {
            System.out.println("No existe un Certificado con el ID: " + id);
            return;
        }

        Certificado certificadoActual = existente.get();
        System.out.println("Datos actuales -> Nombre: " + certificadoActual.getNombre() +
                " | Entidad Emisora: " + certificadoActual.getEntidadEmisora() +
                "| Fecha de Obtencion: " + certificadoActual.getFechaObtencion()
        );
        System.out.println("Ingrese los Datos nuevos:");
        String nombre = consola.leerTexto("Ingrese el nuevo nombre del certificado: ");
        String entidadEmisora = consola.leerTexto("Ingrese la nueva entidad emisora: ");
        LocalDate fechaObtencion = consola.leerFecha("Ingrese la nueva fecha de obtencion: ");

        Optional<Certificado> certificadoActualizado = certificadoService.actualizarCertificado(id, nombre, entidadEmisora, fechaObtencion);

        if (certificadoActualizado.isPresent()) {
            System.out.println("Certificado actualizado con éxito");
        } else {
            System.out.println("No se pudo actualizar el certificado con el id: " + id);
        }
    }

    private void eliminar() {
        int id = consola.leerEntero("Ingrese el id del certificado a eliminar:");

        if (certificadoService.eliminarCertificadoPorId(id)){
            System.out.println("Se elimino el certificado con ID: " + id);
        } else  {
            System.out.println("No existe un certificado con el ID: " + id);
        }
    }

    public void mostrarMenuCertificados() {
        int opcion = 0;
        do {
            System.out.println("""
                ======= ¿Que vas a Realizar? ========
                1. Registrar Certificado.
                2. Mostrar todos los Certificados.
                3. Buscar un Certificado por ID.
                4. Actualizar Certificado.
                5. Eliminar Certificado.
                6. Regresar al Menú Principal.
                """);
           opcion = consola.leerEntero("Elige una opcion:");
           switch (opcion) {
               case 1:
                   guardar();
                   break;
               case 2:
                   listar();
                   break;
               case 3:
                   buscarId();
                   break;
               case 4:
                   actualizar();
                   break;
               case 5:
                   eliminar();
                   break;
               case 6:
                   System.out.println("Regresar al menu principal");
                   break;
               default:
                   System.out.println("Ingresa una opcion del menu que sea valida.");
           }
        } while (opcion != 6);
    }
}


