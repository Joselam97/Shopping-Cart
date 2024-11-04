package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

//carro/* es para acceder a las rutas que tengan 'carro' en comun despues de un /
@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*", "/usuarios/form/*", "/usuarios/eliminar/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //crea una instancia del servicio login para verificar si el usuario esta autenticado
        LoginService service = new LoginServiceSessionImpl();
        //obtiene el nombre del usuario de la sesion actual
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        //convierte el objeto request a HttpServletRequest para obtener el path de la solicitud
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //obtiene el path de la solicitud actual
        String path = httpRequest.getServletPath();
        // Permitir acceso público a la lista de productos, excluyendo las rutas del formulario y eliminacion
        if (path.startsWith("/productos") && !path.startsWith("/productos/form") && !path.startsWith("/productos/eliminar")) {
            chain.doFilter(request, response);
            return;
        }

        //si el usuario esta autenticado, continua con la cadena de filtros o el servlet
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            //si el usuario no esta autenticado envia un error 404
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, no estás autorizado para entrar a esta página!");
        }
    }
}