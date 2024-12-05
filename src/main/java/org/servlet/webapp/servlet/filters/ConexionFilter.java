package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.service.ServiceJDBCException;

import java.io.IOException;

// The WebFilter annotation specifies this filter applies to all requests ("/*")
@WebFilter("/*")
public class ConexionFilter implements Filter {

    // The doFilter method is called for every incoming HTTP request
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            // Continue the filter chain and forward the request and response to the next filter or servlet
            chain.doFilter(request, response);

        } catch (ServiceJDBCException e) {
            // Catch any ServiceJDBCException thrown by the servlet or other filters
            // Set the HTTP status code to 500 (Internal Server Error) and send the exception message
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            // Print the stack trace for debugging purposes (not recommended in production code)
            e.printStackTrace();
        }
    }
}
