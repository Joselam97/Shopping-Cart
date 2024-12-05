package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

// The WebServlet annotation maps this servlet to the "/carro/actualizar" URL pattern
@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {

    // Inject the Carro object which holds the shopping cart data
    @Inject
    private Carro carro;

    // The doPost method handles HTTP POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Call method to update the products to be removed from the cart
        updateProductos(req, carro);
        // Call method to update the quantities of products in the cart
        updateCantidades(req, carro);

        // Redirect to the shopping cart view page after updating
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }


    // Helper method to handle removal of products from the cart
    private void updateProductos(HttpServletRequest request, Carro carro) {
        // Retrieve the "deleteProductos" parameter values (products to be deleted)
        String[] deleteIds = request.getParameterValues("deleteProductos");

        // If there are products to remove, process them
        if (deleteIds != null && deleteIds.length > 0) {
            // Convert the array of product IDs to a List
            List<String> productoIds = Arrays.asList(deleteIds);
            // Call the removeProductos method to remove products from the cart
            carro.removeProductos(productoIds);
        }
    }

    // Helper method to update the quantities of products in the cart
    private void updateCantidades(HttpServletRequest request, Carro carro) {
        // Get all the parameter names from the request
        Enumeration<String> enumeration = request.getParameterNames();

        // Iterate through the parameter names to find those starting with "cant_"
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            // Check if the parameter name starts with "cant_"
            if (paramName.startsWith("cant_")) {
                // Extract the product ID from the parameter name
                String id = paramName.substring(5);
                // Get the quantity for this product
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    // Update the quantity for the product in the cart
                    carro.updateCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
