package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.models.entities.Producto;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.util.Optional;

// URL mapping for product deletion functionality
@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {


    // Injecting the service responsible for handling product-related operations
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

// Attempting to retrieve the product ID from the URL parameter "id"
        long id;
        try {
            id = Long.parseLong(req.getParameter("id")); // Parsing the ID as a long
        } catch (NumberFormatException e) {
            // If parsing fails, default the ID to 0 (invalid)
            id = 0L;
        }

        // Check if the ID is valid (greater than 0)
        if (id > 0) {
            // Fetching the product using the service
            Optional<Producto> o = service.findById(id);

            // If the product exists, proceed with the deletion
            if (o.isPresent()) {
                service.eliminar(id); // Deleting the product from the database
                resp.sendRedirect(req.getContextPath() + "/productos"); // Redirect to the products list page
            } else {
                // If no product is found with the given ID, send a NOT_FOUND error
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la base de datos!");
            }
        } else {
            // If the ID is invalid (<= 0), send an error response
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
