package org.servlet.webapp.servlet.service;

// Custom exception to handle errors related to JDBC operations
public class ServiceJDBCException extends RuntimeException{

    // Constructor that takes a message and passes it to the superclass (RuntimeException)
    public ServiceJDBCException(String message) {
        super(message);
    }

    // Constructor that takes both a message and a cause (Throwable), passing both to the superclass
    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
