package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.models.Carro;
import org.servlet.webapp.servlet.models.ItemCarro;
import org.servlet.webapp.servlet.models.entities.Producto;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.util.Optional;

// URL mapping for the Add to Cart functionality
@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    // Injecting the principal service for Producto
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    // Injecting the Carro (Shopping Cart) object
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieving the 'id' parameter from the request to identify the product to add to the cart
        Long id = Long.parseLong(req.getParameter("id"));


        // Fetching the product using the service, wrapped in an Optional to safely handle absence
        Optional<Producto> producto = service.findById(id);

        // Checking if the product exists
        if (producto.isPresent()){
            // Creating a new item to be added to the cart with a quantity of 1
            ItemCarro item = new ItemCarro(1,producto.get());

            // Checking if 'carro' (cart) is null and initializing it if necessary
            if (carro == null) {
                // If null, initialize a new empty cart
                carro = new Carro();
            }
            carro.addItemCarro(item);  // Adding the created item to the cart
        }
        // Redirecting the user to the view cart page after adding the item
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
