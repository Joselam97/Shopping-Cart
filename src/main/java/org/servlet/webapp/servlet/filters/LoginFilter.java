package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

// The filter applies to specific paths
@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*", "/usuarios/form/*", "/usuarios/eliminar/*"})
public class LoginFilter implements Filter {

    // The doFilter method is executed for each HTTP request matching the filter's URL pattern
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Create a LoginService instance to check if the user is logged in
        LoginService service = new LoginServiceSessionImpl();

        // Get the username of the logged-in user, if present
        Optional<String> username = service.getUsername((HttpServletRequest) request);

        // Cast the ServletRequest to HttpServletRequest to access path-specific methods
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Get the servlet path of the current request
        String path = httpRequest.getServletPath();

        // If the path starts with "/productos" but does not match the form or delete actions, allow the request to pass
        if (path.startsWith("/productos") && !path.startsWith("/productos/form") && !path.startsWith("/productos/eliminar")) {
            chain.doFilter(request, response); // Allow the request to proceed without further checks
            return;
        }

        // If a valid username is present (the user is logged in), allow the request to proceed
        if (username.isPresent()) {
            chain.doFilter(request, response); // Continue with the request processing
        } else {
            // If no username is present (user is not logged in), return a 401 Unauthorized error with a message
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, no estás autorizado para entrar a esta página!"); // Custom unauthorized message
        }
    }
}