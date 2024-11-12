package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.service.ServiceJDBCException;
import org.servlet.webapp.servlet.util.ConexionJdbcDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try(Connection conn = ConexionJdbcDS.getConnection()){

            if (conn == null) {
                System.err.println("Conexión es null en el filtro.");
            } else {
                System.out.println("Conexión establecida en el filtro.");
            }

            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }

            try {
                request.setAttribute("conn",conn);
                chain.doFilter(request,response);
                conn.commit();
            } catch (SQLException | ServiceJDBCException e){
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException | NamingException throwables){
            throwables.printStackTrace();
        }
    }
}
