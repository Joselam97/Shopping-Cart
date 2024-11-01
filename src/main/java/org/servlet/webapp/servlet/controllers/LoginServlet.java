package org.servlet.webapp.servlet.controllers;

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
import org.servlet.webapp.servlet.service.UsuarioServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

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
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Hola " + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1 style='color: green;'>Login Correcto!</h1>");
                out.println("        <h3>Hola " + usernameOptional.get() + " has iniciado sesion con exito</h3>");
                out.println("        <p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("        <p><a href='" + req.getContextPath() + "/logout'>cerrar sesion</a></p>");
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

        UsuarioService service = new UsuarioServiceImpl((Connection) req.getAttribute("conn"));
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
