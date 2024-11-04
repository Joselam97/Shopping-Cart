package org.servlet.webapp.servlet.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/*Implementacion del servicio de autenticacion basada en sesion
Proporciona un metodo para obtener el nombre de usuario autenticado a partir de la sesion*/
public class LoginServiceSessionImpl implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        //Obtiene la sesion del usuario
        HttpSession session = request.getSession();

        //Intenta obtener el nombre de usuario almacenado en la sesion
        String username = (String) session.getAttribute("username");
        //Devuelve el nombre de usuario si esta presente, de lo contrario devuelve un Optional vacio
        if (username != null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
