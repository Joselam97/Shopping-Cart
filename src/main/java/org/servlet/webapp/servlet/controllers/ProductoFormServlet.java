package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.ProductoService;
import org.servlet.webapp.servlet.service.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtiene la conexion de la base de datos
        Connection conn = (Connection) req.getAttribute("conn");
        //crea una instancia del servicio de productos usando la base de datos
        ProductoService service = new ProductoServiceJDBCImpl(conn);

        //inicializa el id del producto a editar
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        //crea un nuevo objeto Producto y asigna a una categoria vacia
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        //si el id es valido, busca el producto en la base de datos
        if (id > 0) {
            Optional<Producto> o = service.findById(id);
            if (o.isPresent()) {
                producto = o.get();
            }
        }

        //agrega las categorias y el producto como atributos de la solicitud para el formulario
        req.setAttribute("categorias", service.listarCategoria());
        req.setAttribute("producto",producto);
        req.setAttribute("title",req.getAttribute("title") + ": Formulario de Productos");
        //redirige al formulario de producto
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        //obtiene y valida los parametros del formulario
        String nombre = req.getParameter("nombre");

        //convierte el precio a integer, en caso de error asigna 0
        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");

        //convierte el id de la categoria a Long, en caso de error lo asigna a 0
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        //mapa para almacenar los errores de validacion
        Map<String, String> errores = new HashMap<>();
        //validacion de los campos requeridos y sus restricciones
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "el nombre es requerido!");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "el sku es requerido!");
        } else if (sku.length() > 10){
            errores.put("sku","el sku debe tener como maximo 10 caracteres!");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "la fecha es requerida");
        }
        if (precio.equals(0)) {
            errores.put("precio", "el precio es requerido!");
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "la categoria es requerida!");
        }


//convierte la fecha a LocalDate, en caso de error asigna null
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e ) {
            fecha = null;
        }
        //convierte el id del producto a long, en caso de error le asigna 0L
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        //crea y asigna los valores al objeto Producto
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        //crea y asigna una categoria al producto
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        //verifica si no hay errores
        if (errores.isEmpty()) {
            //guarda el producto en la base de datos
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            //si hay errores, los agrega a la solicitud y redirige al formulario
            req.setAttribute("errores",errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto",producto);
            req.setAttribute("title",req.getAttribute("title") + ": Formulario de Productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
