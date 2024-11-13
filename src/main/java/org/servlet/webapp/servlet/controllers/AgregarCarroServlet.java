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
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.service.ProductoService;

import java.io.IOException;
import java.util.Optional;

//ruta url
@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));


        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get());

            if (carro == null) {
                carro = new Carro();
            }
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
