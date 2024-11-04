package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Crea una instancia del servicio de autenticacion
        LoginService auth = new LoginServiceSessionImpl();
        //Obtiene el nombre de usuario de la sesion actual
        Optional<String> username = auth.getUsername(req);

        //si el usuario ha iniciado sesion, invalida la sesion actual
        if (username.isPresent()){
            HttpSession session = req.getSession();
            //invalida la sesion para cerrar la sesion del usuario
            session.invalidate();

        }
        //redirige al usuario a la pagina login despues de cerrar sesion
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
