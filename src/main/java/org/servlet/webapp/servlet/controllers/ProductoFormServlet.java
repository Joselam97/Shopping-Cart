package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// URL mapping for the product form page
@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    // Inject the service responsible for handling product-related business logic
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        // Attempt to retrieve the product ID from the URL parameter "id"
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            // If invalid ID, default to 0
            id = 0L;
        }

        Producto producto = new Producto(); // Create a new Producto object
        producto.setCategoria(new Categoria()); // Set an empty Categoria (category)

        // If the ID is valid, try to fetch the existing product from the database
        if (id > 0) {
            Optional<Producto> o = service.findById(id);
            if (o.isPresent()) {
                producto = o.get(); // Set the product from the database if found
            }
        }

        // Set attributes for the JSP page to use
        req.setAttribute("categorias", service.listarCategoria()); // List categories for the dropdown
        req.setAttribute("producto",producto); // Pass the product (either new or fetched)
        req.setAttribute("title",req.getAttribute("title") + ": Formulario de Productos"); // Set page title
        // Forward to the form.jsp page
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve parameters from the form
        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            // Default price to 0 if parsing fails
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");

        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            // Default category ID to 0 if parsing fails
            categoriaId = 0L;
        }

        // Validate form inputs and store any errors
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            // Required field validation for 'nombre'
            errores.put("nombre", "el nombre es requerido!");
        }
        if (sku == null || sku.isBlank()) {
            // Required field validation for 'sku'
            errores.put("sku", "el sku es requerido!");
        } else if (sku.length() > 10){
            errores.put("sku","el sku debe tener como maximo 10 caracteres!"); // SKU length validation
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "la fecha es requerida"); // Date required field validation
        }
        if (precio.equals(0)) {
            errores.put("precio", "el precio es requerido!"); // Price required field validation
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "la categoria es requerida!"); // Category required field validation
        }

        // Parse the registration date
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e ) {
            fecha = null; // If date parsing fails, set the date to null
        }

        Long id;
        // Attempt to retrieve the product ID (if updating an existing product)
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null; // Default to null if invalid ID
        }

        // Create a new Producto object with the input data
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        // Create a Categoria object and set it on the Producto
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        // If there are no validation errors, save the product and redirect
        if (errores.isEmpty()) {
            service.guardar(producto); // Save the product to the database
            resp.sendRedirect(req.getContextPath() + "/productos"); // Redirect to the products listing page
        } else {
            // If there are errors, forward the request back to the form page with error messages
            req.setAttribute("errores",errores);
            req.setAttribute("categorias", service.listarCategoria()); // Pass the categories list again
            req.setAttribute("producto",producto); // Pass the (incomplete) product data
            req.setAttribute("title",req.getAttribute("title") + ": Formulario de Productos"); // Set page title

            // Forward to form.jsp
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
