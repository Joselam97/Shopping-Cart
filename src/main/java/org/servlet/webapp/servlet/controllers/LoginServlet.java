package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.models.entities.Usuario;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.UsuarioService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

// URL mapping for both '/login' and '/login.html' paths
@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    // Injecting LoginService to handle login logic (checking user credentials)
    @Inject
    private LoginService auth;

    // Injecting UsuarioService to handle user-related operations like authentication
    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieving the username from the session or request (using LoginService)
        Optional<String> usernameOptional = auth.getUsername(req);

        // If a username exists (meaning the user is already logged in)
        if (usernameOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8"); // Setting response content type to HTML with UTF-8 encoding
            try (PrintWriter out = resp.getWriter()) {

                // Writing the HTML response with inline styles and greeting the user
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset='UTF-8'>");
                out.println("        <title>Hola " + usernameOptional.get() + "</title>"); // Page title with the username

                // Adding inline CSS to style the page
                out.println("        <style>");
                out.println("            body { font-family: Arial, sans-serif; }");
                out.println("            .highlight { border-radius: 5px; background-color: #f9f9f9; padding: 10px; }"); // Gray Background-color with border-radius
                out.println("            h1 { color: blue; font-family: Arial, sans-serif; }"); // Style for h1
                out.println("            a { font-family: Arial, sans-serif; color: #007bff; text-decoration: none; }"); // Style for links
                out.println("            a:hover { text-decoration: underline; }"); // Adding hover effect for links
                out.println("        </style>");

                out.println("    </head>");

                out.println("    <body>");
                out.println("        <h1>Login Correcto!</h1>"); // Success message indicating the user has logged in
                out.println("        <h3 class='highlight'>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito</h3>"); // Greeting the user

                // Providing links to go back or log out
                out.println("        <p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("        <p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");

                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            // If the user is not logged in, forward to the login page
            req.setAttribute("title",req.getAttribute("title") + ": Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extracting the username and password from the request parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Calling the service to check if the user exists with the provided credentials
        Optional<Usuario> usuarioOptional = service.login(username,password);

        // If the user is authenticated
        if (usuarioOptional.isPresent()){

            // Creating a new session and storing the username
            HttpSession session = req.getSession();
            session.setAttribute("username",username);

            // Redirecting to the login page after a successful login
            resp.sendRedirect(req.getContextPath() + "/login.html");

        } else {
            // If authentication fails, send an unauthorized error with a message
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar a esta pagina!");
        }
    }
}
