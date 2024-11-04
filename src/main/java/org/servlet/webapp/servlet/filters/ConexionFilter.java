package org.servlet.webapp.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.service.ServiceJDBCException;
import org.servlet.webapp.servlet.util.ConexionJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //intenta obtener una conexion a la base de datos
        try(Connection conn = ConexionJDBC.getConnection()){
            //verifica si la conexion es nula y muestra un mensaje en la consola
            if (conn == null) {
                System.err.println("Conexión es null en el filtro.");
            } else {
                System.out.println("Conexión establecida en el filtro.");
            }

            //desactiva el auto-commit de la conexion para manejar transacciones manualmente
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }

            try {
                //almacena la conexion en el request para que este disponible en los servlets
                request.setAttribute("conn",conn);
                //continua con el siguiente filtro o servlet en la cadena
                chain.doFilter(request,response);
                //si no hay excepciones, realiza commit de la transaccion
                conn.commit();
            } catch (SQLException | ServiceJDBCException e){
                //si ocurre una excepcion, realiza rollback de la transaccion
                conn.rollback();
                //envia un error 500 al cliente con el mensaje de la excepcion
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                //imprime la traza de la excepcion para propositos de depuracion
                e.printStackTrace();
            }

        } catch (SQLException throwables){
            //imprime cualquier excepcion de SQL que ocurra al intentar obtener la conexion
            throwables.printStackTrace();
        }
    }
}
