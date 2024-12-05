package org.servlet.webapp.servlet.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

// The bean is created once per application and shared across the application
@ApplicationScoped
public class ProducerResources {

    @Inject
    // Logger instance for logging events
    private Logger log;

    // Injection of the DataSource (configured in JNDI) for MySQL
    @Resource(lookup = "java:/MySqlDS")
    private DataSource ds;

    // Injection of the EntityManagerFactory for JPA persistence
    @PersistenceUnit(name = "ejemploJpa")
    private EntityManagerFactory emf;

    // Produces a Connection object that is request-scoped
    @Produces
    @RequestScoped  // The connection bean is created and available for the duration of a request
    @MysqlConn // Qualifier to distinguish this bean as representing a MySQL connection
    private Connection beanConnection() throws NamingException, SQLException {
        // Retrieves a connection from the DataSource configured in JNDI
        return ds.getConnection();
    }

    // Produces a Logger instance for the class where it is injected
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        // Creates a logger specific to the class where the logger is injected
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    // Disposes of the Connection after the request is finished
    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        // Closes the MySQL connection when it's no longer needed
        connection.close();
        log.info("Cerrando la Conexion en Base de Datos MySql!");
    }


    // Produces an EntityManager instance for JPA interactions, scoped to the request
    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        // Creates a new EntityManager instance using the EntityManagerFactory
        return emf.createEntityManager();
    }

    // Disposes of the EntityManager after use
    public void close(@Disposes EntityManager entityManager) {
        // Checks if the EntityManager is open before closing it
        if (entityManager.isOpen()) {
            entityManager.close();
            log.info("Cerrando la Conexion del EntityManager!");
        }
    }
}
