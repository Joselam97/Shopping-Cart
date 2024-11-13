package org.servlet.webapp.servlet.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.service.ServiceJDBCException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try  {

            Connection connRequest = this.conn;

            if (conn == null) {
                System.err.println("Conexión es null en el filtro.");
            } else {
                System.out.println("Conexión establecida en el filtro.");
            }

            if (connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }

            try {
                chain.doFilter(request,response);
                connRequest.commit();
            } catch (SQLException | ServiceJDBCException e){
                connRequest.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
