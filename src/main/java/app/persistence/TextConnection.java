package app.persistence;

import app.persistence.dbconnection.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TextConnection {
    public static void main(String[] args) {
        try {
            Connection con = DataBaseConnection.getInstance().getConnection();

            System.out.println("Cerrada? " + con.isClosed());
            System.out.println("Base: " + con.getCatalog());

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT DB_NAME(), @@VERSION")) {
                if (rs.next()) {
                    System.out.println("Base actual: " + rs.getString(1));
                    System.out.println("Motor: " + rs.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
