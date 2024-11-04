package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.models.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

//ruta para la clase
@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // obtiene la sesion actual del usuario
        HttpSession session = req.getSession();

        //verifica si existe un objeto "carro" en la sesion
        if (session.getAttribute("carro") != null){
        //recupera el objeto Carro de la sesion
            Carro carro = (Carro) session.getAttribute("carro");
           // actualiza los productos y las cantidades en el carro
            updateProductos(req,carro);
            updateCantidades(req,carro);
        }
        //redirecciona al usuario a la vista de carro  '/carro/ver' despues de la actualizacion
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }


    /**Elimina productos del carro basandose en IDs recibidos en la solicitud
     *
     * @param request El objeto HttpServletRequest con los los parametros enviados por el usuario
     * @param carro el Objeto Carro que representa al carro de compras
     */
    private void updateProductos(HttpServletRequest request, Carro carro){
        //Obtiene los IDs de los productos a eliminar de los parametros de la solicitud
        String[] deleteIds = request.getParameterValues("deleteProductos");

        //verifica si hay productos seleccionados para eliminar
        if (deleteIds != null && deleteIds.length > 0){
            //convierte el arreglo de IDs en una lista
            List<String> productoIds = Arrays.asList(deleteIds);
            //Elimina los productos del carro usando los IDs de la lista
            carro.removeProductos(productoIds);
        }
    }


    /**
     * Actualiza las cantidades de los productos en el carro segun los parametros de la solicitud
     * @param request el objeto HttpServletRequest con los parametros enviados por el usuario
     * @param carro El ojeto Carro que representa el carro de compras
     */
    private void updateCantidades(HttpServletRequest request, Carro carro){
//Obtiene todos los nombres de parametros de la solicitud
        Enumeration<String> enumeration = request.getParameterNames();

        //itera sobre cada parametro
        while(enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            //verifica si el parametro es de cantidad
            if (paramName.startsWith("cant_")){
                //obtiene el id producto eliminando el prefijo "cant_"
                String id = paramName.substring(5);
                //obtiene el valor de la cantidad del producto
                String cantidad = request.getParameter(paramName);
                //verifica que la cantidad no sea nula y actualiza el producto en el carro
                if (cantidad != null){
                    carro.updateCantidad(id,Integer.parseInt(cantidad));
                }
            }
        }
    }
}
