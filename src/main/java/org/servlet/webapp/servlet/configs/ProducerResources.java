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

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Resource(lookup = "java:/MySqlDS")
    private DataSource ds;

    @PersistenceUnit(name = "ejemploJpa")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {

        return ds.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("Cerrando la Conexion en Base de Datos MySql!");
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
            log.info("Cerrando la Conexion del EntityManager!");
        }
    }
}
