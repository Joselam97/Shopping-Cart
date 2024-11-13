package org.servlet.webapp.servlet.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object login(InvocationContext invocation) throws Exception {

        log.info(" ***** Entrando Antes de Invocar el Metodo " + invocation.getMethod().getName() +
                " de la Clase " + invocation.getMethod().getDeclaringClass());

        Object resultado = invocation.proceed();

        log.info(" ***** Saliendo de la Invocacion del Metodo " + invocation.getMethod().getName());

        return resultado;
    }
}
