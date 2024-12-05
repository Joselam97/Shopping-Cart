package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

// The filter applies to all URL paths (/*), meaning it will measure the response time for all requests
@WebFilter("/*")
public class TiempoTranscurridoFilter implements Filter {

    // Logger to log the time taken for the request processing
    private static final Logger logger = Logger.getLogger("TiempoTranscurridoFilter");

    // The method that is executed for every HTTP request
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Record the current time at the start of the request processing
        long inicio = System.currentTimeMillis();

        // Continue with the request processing by passing it to the next filter or servlet
        filterChain.doFilter(servletRequest,servletResponse);

        // Record the current time after the request has been processed
        long fin = System.currentTimeMillis();

        // Calculate the time taken to process the request (in milliseconds)
        long resultado = fin - inicio;


        // Log the time taken to process the request
        logger.info(String.format("El tiempo de carga de la pagina es de %s milisegundos ", resultado));
    }
}
