package org.servlet.webapp.servlet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase ya no se usa en el proyecto, porque ahora usa Pool de Conexiones
public class ConexionJDBC {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "SYSTEM";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
