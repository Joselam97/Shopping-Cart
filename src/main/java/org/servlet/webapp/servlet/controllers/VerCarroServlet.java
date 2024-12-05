package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// The WebServlet annotation maps this servlet to the "/carro/ver" URL pattern
@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {

    // The doGet method handles HTTP GET requests
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Set the title attribute in the request to append ": Carro de Compras"
        // This can be used in the JSP to display a dynamic page title
        req.setAttribute("title",req.getAttribute("title") + ": Carro de Compras");


        // Forward the request to the "carro.jsp" page for rendering
        // The request attributes (like title) will be available in the JSP
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req,resp);
    }
}