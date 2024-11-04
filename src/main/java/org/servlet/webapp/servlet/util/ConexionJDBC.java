package org.servlet.webapp.servlet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Clase de utilidad para gestionar la conexion JDBC a la base de datos de MySQL
Proporciona un metodo estatico para obtener una conexion con los parametros especificados*/
public class ConexionJDBC {

    //URL de la base de datos
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    //Username de la base de datos
    private static String username = "root";
    //Password de la base de datos
    private static String password = "SYSTEM";

    //Obtiene una conexion a la base de datos
    public static Connection getConnection() {
        try {
            //carga el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Retorna la conexion a la base de datos
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            //Manejo de excepcion si no se encuentra el controlador de MySQL
            e.printStackTrace();
        } catch (SQLException e) {
            //Manejo de excepcion si ocurre un error al conectar
            e.printStackTrace();
        }
        //retorna null si ocurre un error
        return null;
    }
}
