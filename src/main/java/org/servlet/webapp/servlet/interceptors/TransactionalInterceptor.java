package org.servlet.webapp.servlet.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.service.ServiceJDBCException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJDBC
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        if (conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }

        try{
            log.info(" ------> Iniciando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            Object resultado = invocation.proceed();
            conn.commit();

            log.info(" ------> Realizando Commit y Finalizando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            return resultado;

        } catch (ServiceJDBCException e){
            conn.rollback();
            throw e;
        }
    }
}
