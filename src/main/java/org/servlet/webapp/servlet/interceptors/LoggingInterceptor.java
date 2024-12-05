package org.servlet.webapp.servlet.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;


@Logging // The @Logging annotation marks this class as an interceptor
@Interceptor // that will be applied to methods or classes annotated with @Logging.

// Marks this class as an interceptor.
public class LoggingInterceptor {

    // Injecting the Logger instance to log the method execution details
    @Inject
    private Logger log;

    // @AroundInvoke marks this method as the point of interception.
    // It will wrap the target method's invocation.
    @AroundInvoke
    public Object login(InvocationContext invocation) throws Exception {

        // Log the method name and class name before the method is invoked.
        log.info(" ***** Entrando Antes de Invocar el Metodo " + invocation.getMethod().getName() +
                " de la Clase " + invocation.getMethod().getDeclaringClass());

        // Proceed with the actual method invocation.
        Object resultado = invocation.proceed();

        // Log the method name after the method invocation completes.
        log.info(" ***** Saliendo de la Invocacion del Metodo " + invocation.getMethod().getName());

        // Return the result of the invoked method.
        return resultado;
    }
}
