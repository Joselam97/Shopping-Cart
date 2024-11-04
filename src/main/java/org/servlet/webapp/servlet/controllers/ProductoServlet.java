package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.LoginService;
import org.servlet.webapp.servlet.service.LoginServiceSessionImpl;
import org.servlet.webapp.servlet.service.ProductoService;
import org.servlet.webapp.servlet.service.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;


@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        //crea una instancia del servicio de productos usando la base de datos
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        //obtiene la lista de todos los productos de la base de datos
        List<Producto> productos = service.listar();

        //crea una instancia del servicio de autenticacion y obtiene el nombre de usuario
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        //agrega los productos, username y titulo como atributos de la solicitud
        req.setAttribute("productos",productos);
        req.setAttribute("username",usernameOptional);
        req.setAttribute("title","Lista de Productos");
        //redirige a la pagina jsp que muestra la lista de productos
        getServletContext().getRequestDispatcher("/listar_productos.jsp").forward(req,resp);
    }
}