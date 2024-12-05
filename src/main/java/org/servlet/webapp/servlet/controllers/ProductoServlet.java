package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.models.entities.Producto;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// Define the URL mappings for the servlet
@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    // Inject the service for handling product-related business logic
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    // Inject the service for handling authentication and user sessions
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve the list of products from the service
        List<Producto> productos = service.listar();

        // Check if the user is authenticated by getting the username (optional)
        Optional<String> usernameOptional = auth.getUsername(req);


        // Set attributes for the JSP page to use
        req.setAttribute("productos",productos); // List of products to display
        req.setAttribute("username",usernameOptional); // Username of the authenticated user (if present)
        req.setAttribute("title","Lista de Productos"); // Page title

        // Forward the request to the JSP page for rendering the product list
        getServletContext().getRequestDispatcher("/listar_productos.jsp").forward(req,resp);
    }
}