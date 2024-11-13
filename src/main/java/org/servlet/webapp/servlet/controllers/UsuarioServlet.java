package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;
import org.servlet.webapp.servlet.service.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Usuario> usuarios = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("usuarios",usuarios);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("title","Lista de Usuarios");
        getServletContext().getRequestDispatcher("/listar_usuarios.jsp").forward(req,resp);
    }
}
