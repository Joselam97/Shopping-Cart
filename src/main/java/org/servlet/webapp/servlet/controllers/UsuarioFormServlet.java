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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Maps this servlet to the URL "/usuarios/form_usuarios"
@WebServlet("/usuarios/form_usuarios")
public class UsuarioFormServlet extends HttpServlet {

    // Inject the UsuarioService to handle business logic for user management
    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get the user ID parameter from the request (for updating an existing user)
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L; // If parsing fails, set id to 0 (indicating new user creation)
        }

        // Create a new user object
        Usuario usuario = new Usuario();

        // If the ID is valid, retrieve the user from the service to edit the details
        if (id > 0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                usuario = o.get(); // If the user is found, populate the usuario object with the existing user data
            }
        }

        // Set the user and the page title as request attributes for the JSP
        req.setAttribute("usuario",usuario);
        req.setAttribute("title",req.getAttribute("title") + ": Registro de usuario");

        // Forward the request to the "form_usuarios.jsp" page to display the form
        getServletContext().getRequestDispatcher("/form_usuarios.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

// Parse the user ID to either create a new user or update an existing one
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L; // If parsing fails, set id to 0 for a new user
        }

        // Retrieve form input parameters: username, password, and email
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // Map to store validation errors
        Map<String,String> errores = new HashMap<>();

        // Validate required fields and add errors to the map if necessary
        if (username == null || username.isBlank()){
            errores.put("username","el username es requerido!");
        }

        // Password is required only if creating a new user
        if ((id == 0) && (password == null || password.isBlank())){
            errores.put("password","el password es requerido!");
        }

        if (email == null || email.isBlank()){
            errores.put("email","el email es requerido!");
        }

        // Create a new Usuario object
        Usuario usuario = new Usuario();

        // If the ID is valid, attempt to retrieve the existing user for updating
        if (id > 0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                // Populate the usuario object with existing user data
                usuario = o.get();
            }
        }

        // Set the user fields with the form values
        usuario.setEmail(email);
        usuario.setUsername(username);

        // Only set the password if it's provided
        if (password != null && !password.isBlank()){
            usuario.setPassword(password);
        }

        // If there are no errors, save the user and redirect to the user list
        if (errores.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            // If there are validation errors, forward back to the form page with the errors and form data
            req.setAttribute("errores",errores);
            req.setAttribute("usuario",usuario);
            req.setAttribute("title",req.getAttribute("title") + ": Formulario de usuario");
            getServletContext().getRequestDispatcher("/form_usuarios.jsp").forward(req,resp);
        }
    }
}
