package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //establece el titulo de la pagina agregando el texto "Carro de compras"
        req.setAttribute("title",req.getAttribute("title") + ": Carro de Compras");

        //redirige a la pagina jsp que muestra el contenido del carro de compras
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req,resp);
    }
}