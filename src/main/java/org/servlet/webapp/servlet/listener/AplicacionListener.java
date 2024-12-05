package org.servlet.webapp.servlet.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


// indicates that this class is a listener for web application lifecycle events.
@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener,
        HttpSessionListener {

    private ServletContext servletContext;

    // This method is called when the web application is initialized, typically when the server starts.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Log message when the application is initialized.
        sce.getServletContext().log("inicializando la aplicacion!");

        // Store the ServletContext object for future use.
        servletContext = sce.getServletContext();

        // Set a global attribute for the entire application. This value will be available to all requests.
        servletContext.setAttribute("mensaje","algun valor global de la app!");
    }


    // This method is called when the web application is about to shut down (server stop).
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Log message when the application is destroyed.
        servletContext.log("destruyendo la aplicacion!");
    }


    // This method is called when a new request is initialized.
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // Log message when a request is initialized.
        servletContext.log("inicializando el request!");

        // Set request-specific attributes. These attributes are only available for the current request.
        sre.getServletRequest().setAttribute("mensaje","guardando algun valor para el request");
        sre.getServletRequest().setAttribute("title","Catalogo Servlet");
    }


    // This method is called when the request is about to be destroyed (end of request lifecycle).
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // Log message when the request is destroyed.
        servletContext.log("destruyendo el request!");
    }


    // This method is called when a new HTTP session is created.
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Log message when an HTTP session is created.
        servletContext.log("creando la sesion http!");
    }

    // This method is called when an HTTP session is about to be destroyed.
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Log message when an HTTP session is destroyed.
        servletContext.log("destruyendo la sesion http!");
    }
}
