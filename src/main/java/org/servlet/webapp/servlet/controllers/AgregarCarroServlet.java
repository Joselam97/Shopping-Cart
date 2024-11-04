package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.models.Carro;
import org.servlet.webapp.servlet.models.ItemCarro;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.ProductoService;
import org.servlet.webapp.servlet.service.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

//ruta url
@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //obtiene el parametro "id" de la solicitud y lo convierte a tipo long
        Long id = Long.parseLong(req.getParameter("id"));

        //obtiene la conexion de la base de datos desde los atributos de la solicitud
        Connection conn = (Connection) req.getAttribute("conn");
        //crea un servicio de productos utilizando la implementacion JDBC y la conexion proporcionada
        ProductoService service = new ProductoServiceJDBCImpl(conn);


        //busca el producto en la base de datos usando el id proporcionado
        Optional<Producto> producto = service.findById(id);
        //verifica si el producto esta presente en la base de datos
        if (producto.isPresent()){
            //crea un nuevo ItemCarro con una cantidad inicial de 1 y el producto encontrado
            ItemCarro item = new ItemCarro(1,producto.get());
            //obtiene la sesion actual del usuario
            HttpSession session = req.getSession();
            //intenta obtener el carro de la sesion, si no existe crea una nueva
            Carro carro = (Carro) session.getAttribute("carro");
            if (carro == null) {
                carro = new Carro();
                //almacena el carro en la sesion
                session.setAttribute("carro", carro);
            }
            //agrega el item al carro
            carro.addItemCarro(item);
        }
        //redirecciona al usuario a la vista del carro despues de agregr el producto
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
