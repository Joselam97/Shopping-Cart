package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.entities.Usuario;
import org.servlet.webapp.servlet.service.UsuarioService;

import java.io.IOException;
import java.util.Optional;

// Maps this servlet to the "/usuarios/eliminar" URL
@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {

    // Inject the UsuarioService to handle user-related business logic
    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Attempt to parse the "id" parameter from the request
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L; // If parsing fails, set id to 0
        }

        // If the ID is valid (greater than 0), proceed to delete the user
        if (id > 0){
            // Retrieve the user with the specified ID from the service
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){ // If the user exists
                // Delete the user and redirect to the user list page
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            } else {
                // If the user does not exist, return a 404 error
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuario en la base de datos!");
            }
        } else {
            // If the ID is invalid or missing, return a 404 error with a message
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error! el id es null, se debe enviar como parametro en la url!");
        }
    }
}
