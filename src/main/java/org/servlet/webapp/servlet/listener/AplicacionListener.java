package org.servlet.webapp.servlet.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.servlet.webapp.servlet.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener,
        HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //registro del mensaje al inicializar el contexto de la aplicacion
        sce.getServletContext().log("inicializando la aplicacion!");
        //guarda el contexto de la aplicacion para acceso global
        servletContext = sce.getServletContext();
        //define un atributo global para toda la aplicacion
        servletContext.setAttribute("mensaje","algun valor global de la app!");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //registro de mensaje al inicializar cada solicitud
        servletContext.log("destruyendo la aplicacion!");
    }


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //registro de mensaje al inicializar cada solicitud (request)
        servletContext.log("inicializando el request!");
        //define atributos especificos para cada mensaje
        sre.getServletRequest().setAttribute("mensaje","guardando algun valor para el request");
        sre.getServletRequest().setAttribute("title","Catalogo Servlet");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //registro de mensaje al destruir el request
        servletContext.log("destruyendo el request!");
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //registro de mensaje al crear una nueva sesion Http
        servletContext.log("creando la sesion http!");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //registro de mensaje al destruir una sesion Http
        servletContext.log("destruyendo la sesion http!");
    }
}
