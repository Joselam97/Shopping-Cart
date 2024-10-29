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
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        req.setAttribute("productos",productos);
        req.setAttribute("username",usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);

//        String mensajeRequest = (String) req.getAttribute("mensaje");
//        String mensajeApp = (String) getServletContext().getAttribute("mensaje");
//
//        resp.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = resp.getWriter()) {
//
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("    <head>");
//            out.println("        <meta charset=\"UTF-8\">");
//            out.println("        <title>Listado de Productos</title>");
//            out.println("    </head>");
//            out.println("    <body>");
//            out.println("        <h1>Listado de Productos</h1>");
//            if (usernameOptional.isPresent()) {
//                out.println("     <div style='color: blue;'>Hola username " + usernameOptional.get() + " Bienvenido!</div>");
//            }
//            out.println("         <table>");
//            out.println("        <tr>");
//            out.println("            <th>id</th>");
//            out.println("            <th>nombre</th>");
//            out.println("            <th>tipo</th>");
//
//            if (usernameOptional.isPresent()) {
//                out.println("            <th>precio</th>");
//                out.println("            <th>agregar</th>");
//            }
//
//            out.println("        </tr>");
//
//            productos.forEach(p -> {
//                out.println("<tr>");
//                out.println("<td>" + p.getId() + "</td>");
//                out.println("<td>" + p.getNombre() + "</td>");
//                out.println("<td>" + p.getTipo() + "</td>");
//
//                if (usernameOptional.isPresent()) {
//                    out.println("<td>" + p.getPrecio() + "</td>");
//                    out.println("<td><a href=\"" + req.getContextPath() + "/carro/agregar?id=" + p.getId() + "\">Agregar al carro</a></td>");
//                }
//                out.println("</tr>");
//            });
//
//            out.println("        </table>");
//            out.println("<p>" + mensajeApp + "</p>");
//            out.println("<p>" + mensajeRequest + "</p>");
//            out.println("    </body>");
//            out.println("</html>");

    }
}