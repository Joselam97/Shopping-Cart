package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.ProductoService;
import org.servlet.webapp.servlet.service.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//obtiene la conexion de base de datos desde los atributos de la solicitud
        Connection conn = (Connection) req.getAttribute("conn");
        //crea una instancia del servicio de productos usando la conexion a la base de datos
        ProductoService service = new ProductoServiceJDBCImpl(conn);

        //inicializa el id del producto a eliminar
        long id;
        try {
            //intenta cambiar a long el id del producto desde los parametros de la solicitud
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            //si hay un error en el formato del id, lo asigna a 0 Long
            id = 0L;
        }

        //verifica si el id es valido
        if (id > 0) {
            //busca el producto en la base de datos por el id
            Optional<Producto> o = service.findById(id);
            if (o.isPresent()) {
                //si existe lo elimina
                service.eliminar(id);
                //dirige al usuario a la lista de productos despues del proceso
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                //si el producto no existe envia un error 404 con el mensaje siguiente
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la base de datos!");
            }
        } else {
            //si el id es invalido, envia un error 404 con un mensaje especifico
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
