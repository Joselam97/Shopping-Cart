package org.servlet.webapp.servlet.service;

/*Exception para errores en servicio JDBC
    Extiende  de RunTimeException para manejar erorres de base de datos en los servicios*/
public class ServiceJDBCException extends RuntimeException{

    //Constructor que crea una excepcion con un mensaje especifico
    public ServiceJDBCException(String message) {
        super(message);
    }

    //Constructor que crea una excepcion con un mensaje y una causa especifica
    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
