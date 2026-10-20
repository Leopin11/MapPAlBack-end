package app.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {
    private final Scanner sc;


    public Consola(Scanner sc) {
        this.sc = sc;
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un número válido, por favor.");
                sc.nextLine();     // descarta lo que se escribió mal y vuelve a pedir
            }
        }
    }

    public boolean leerBooleano(String mensaje) {
        while (true) {
            System.out.println(mensaje + " (Verdadero/Falso)");
            String texto = sc.nextLine().trim().toLowerCase();
            if (texto.equals("verdadero") || texto.equals("true") || texto.equals("v")) {
                return true;
            }
            if (texto.equals("falso") || texto.equals("false") || texto.equals("f")) {
                return false;
            }
            System.out.println("Responde Verdadero o Falso.");
        }
    }

    public String leerTexto(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = sc.nextLine();
            if(texto.trim().isEmpty()) {
                System.out.println("El texto no puede estar vacio, Repitelo porfavor");
            } else {
                return texto;
            }
        }
    }
}
