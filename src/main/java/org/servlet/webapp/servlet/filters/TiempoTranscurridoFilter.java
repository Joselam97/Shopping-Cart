package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class TiempoTranscurridoFilter implements Filter {

    //Logger para registrar el tiempo de carga
    private static final Logger logger = Logger.getLogger("TiempoTranscurridoFilter");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //guarda el tiempo inicial antes de procesar la solicitud
        long inicio = System.currentTimeMillis();
        //continua con el siguiente filtro o servlet en la cadena
        filterChain.doFilter(servletRequest,servletResponse);
        //calcula el tiempo final despues de procesar la solicitud
        long fin = System.currentTimeMillis();
        //obtiene el tiempo transcurrido en milisegundos
        long resultado = fin - inicio;

        //registra el tiempo de carga en el log
        logger.info(String.format("El tiempo de carga de la pagina es de %s milisegundos ", resultado));
    }
}
