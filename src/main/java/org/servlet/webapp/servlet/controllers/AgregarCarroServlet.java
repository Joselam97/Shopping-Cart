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
        Long id = Long.parseLong(req.getParameter("id"));

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);


        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            if (carro == null) {
                carro = new Carro();
                session.setAttribute("carro", carro);
            }
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
