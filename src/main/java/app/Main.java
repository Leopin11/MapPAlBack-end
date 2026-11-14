package app;

import app.configuration.Config;
import app.userinterface.UserInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Config config = new Config();
        UserInterface userInterface = config.construirUserInterface(sc);

        userInterface.iniciar();
        sc.close();
    }
}
