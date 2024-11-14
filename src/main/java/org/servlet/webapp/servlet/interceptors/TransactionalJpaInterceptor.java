package org.servlet.webapp.servlet.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.servlet.webapp.servlet.service.ServiceJDBCException;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        try{
            log.info(" ------> Iniciando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            em.getTransaction().begin();
            Object resultado = invocation.proceed();
            em.getTransaction().commit();

            log.info(" ------> Realizando Commit y Finalizando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            return resultado;

        } catch (ServiceJDBCException e){
            em.getTransaction().rollback();
            throw e;
        }
    }
}
