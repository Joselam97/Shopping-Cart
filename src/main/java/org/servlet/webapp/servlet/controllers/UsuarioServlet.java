package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.entities.Usuario;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;
import org.servlet.webapp.servlet.service.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// This annotation maps the servlet to the "/usuarios" URL pattern
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    // Dependency injection of the UsuarioService to manage user data
    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve the list of users from the UsuarioService
        List<Usuario> usuarios = service.listar();

        // Instantiate the LoginService to get the logged-in user's information
        LoginService auth = new LoginServiceSessionImpl(); // Use a session-based login service implementation
        Optional<String> usernameOptional = auth.getUsername(req); // Get the username from the session if available

        // Set attributes for the request to pass data to the JSP
        req.setAttribute("usuarios",usuarios); // The list of users to be displayed
        req.setAttribute("username",usernameOptional); // The username of the logged-in user (optional)
        req.setAttribute("title","Lista de Usuarios"); // Page title for the JSP

        // Forward the request to the "listar_usuarios.jsp" page for rendering the user list
        getServletContext().getRequestDispatcher("/listar_usuarios.jsp").forward(req,resp);
    }
}
