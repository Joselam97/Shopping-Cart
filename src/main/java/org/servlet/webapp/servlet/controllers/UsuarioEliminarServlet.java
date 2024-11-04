package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.service.UsuarioService;
import org.servlet.webapp.servlet.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        //inicializa el id del usuario a eliminar
        long id;
        try{
            //cambia el id a Long desde los parametros de la solicitud
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            //si hay un error en el formato, le asigna 0L
            id = 0L;
        }

        //verifica si el id es valido
        if (id > 0){
            //busca el usuario en la base de datos por su id
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                //si el usuario existe lo elimina
                service.eliminar(id);
                //redirige al usuario a la lista de usuarios despues de la eliminacion
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            } else {
                //si el usuario no se encuentra envia el mensaje de error 404
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuario en la base de datos!");
            }
        } else {
            //si el id es invalido envia un mensaje de error 404
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error! el id es null, se debe enviar como parametro en la url!");
        }
    }
}
