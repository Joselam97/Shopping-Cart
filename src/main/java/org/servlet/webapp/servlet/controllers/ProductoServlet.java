package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        req.setAttribute("productos",productos);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("title","Lista de Productos");
        getServletContext().getRequestDispatcher("/listar_productos.jsp").forward(req,resp);
    }
}