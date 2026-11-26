package app.persistence.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private final Connection connection;
    private static final String url = "jdbc:sqlserver://localhost\\SQLEXPRESS02;databaseName=MAPPALDB;"
            + "encrypt=true;trustServerCertificate=true";
    private static final String user = "usuarioJava";
    private static final String password = "Clave123*";

    private DataBaseConnection()  throws SQLException {
        this.connection = DriverManager.getConnection(url,user,password);
    }

    public static synchronized DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
