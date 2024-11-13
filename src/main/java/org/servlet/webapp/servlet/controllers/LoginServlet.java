package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;
import org.servlet.webapp.servlet.service.UsuarioService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset='UTF-8'>");
                out.println("        <title>Hola " + usernameOptional.get() + "</title>");

                // Añadiendo estilo CSS en línea
                out.println("        <style>");
                out.println("            body { font-family: Arial, sans-serif; }");
                out.println("            .highlight { border-radius: 5px; background-color: #f9f9f9; padding: 10px; }"); // Fondo gris muy claro con border-radius
                out.println("            h1 { color: blue; font-family: Arial, sans-serif; }"); // Estilo para el título h1
                out.println("            a { font-family: Arial, sans-serif; color: #007bff; text-decoration: none; }"); // Estilo para los enlaces
                out.println("            a:hover { text-decoration: underline; }"); // Efecto hover para enlaces
                out.println("        </style>");

                out.println("    </head>");

                out.println("    <body>");
                out.println("        <h1>Login Correcto!</h1>");
                out.println("        <h3 class='highlight'>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito</h3>");

               // Enlaces con estilo
                out.println("        <p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("        <p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");

                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            req.setAttribute("title",req.getAttribute("title") + ": Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<Usuario> usuarioOptional = service.login(username,password);

        if (usuarioOptional.isPresent()){

            HttpSession session = req.getSession();
            session.setAttribute("username",username);

            resp.sendRedirect(req.getContextPath() + "/login.html");

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar a esta pagina!");
        }
    }
}
