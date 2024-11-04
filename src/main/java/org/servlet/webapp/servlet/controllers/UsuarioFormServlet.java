package org.servlet.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.service.UsuarioService;
import org.servlet.webapp.servlet.service.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form_usuarios")
public class UsuarioFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        //crea una instancia de servicio usando la conexion de base de datos
        UsuarioService service = new UsuarioServiceImpl(conn);

        //inicializa el id del usuario a editar
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }
        //crea un nuevo objeto Usuario vacio
        Usuario usuario = new Usuario();

        //si el id es valido, busca el usuario en la base de datos
        if (id > 0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                usuario = o.get();
            }
        }

        //agrega el usuario como atributo de la solicitud y configura el titulo de la pagina
        req.setAttribute("usuario",usuario);
        req.setAttribute("title",req.getAttribute("title") + ": Registro de usuario");

        //redirige al formulario de usuario
        getServletContext().getRequestDispatcher("/form_usuarios.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        //inicializa el id del usuario
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        //obtiene los parametros del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        //mapa para almacenar los errores de validacion
        Map<String,String> errores = new HashMap<>();

        //validacion de los campos requeridos
        if (username == null || username.isBlank()){
            errores.put("username","el username es requerido!");
        }

        if ((id == 0) && (password == null || password.isBlank())){
            errores.put("password","el password es requerido!");
        }

        if (email == null || email.isBlank()){
            errores.put("email","el email es requerido!");
        }

        //crea un nuevo objeto Usuario o carga el existente si el id es valido
        Usuario usuario = new Usuario();

        if (id > 0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                usuario = o.get();
            }
        }

        //asigna los valores al usuario
        usuario.setEmail(email);
        usuario.setUsername(username);

        //solo actualiza el password si se proporciona uno nuevo
        if (password != null && !password.isBlank()){
            usuario.setPassword(password);
        }

        //si no hay errores, guarda el usuario en la base de datos y redirige a la lista de usuarios
        if (errores.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            //si hay errores, los agrega a la solicitud y redirige al formulario con los datos ingresados
            req.setAttribute("errores",errores);
            req.setAttribute("usuario",usuario);
            req.setAttribute("title",req.getAttribute("title") + ": Formulario de usuario");
            getServletContext().getRequestDispatcher("/form_usuarios.jsp").forward(req,resp);
        }
    }
}
