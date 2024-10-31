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
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Permitir acceso público a la lista de productos
        String path = httpRequest.getServletPath();
        if (path.startsWith("/productos") && !path.startsWith("/productos/form") && !path.startsWith("/productos/eliminar")) {
            chain.doFilter(request, response);
            return;
        }

        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, no estás autorizado para entrar a esta página!");
        }
    }
}