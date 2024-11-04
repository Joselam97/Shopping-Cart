package org.servlet.webapp.servlet.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

/*Implementacion del servicio de autenticacion basada en Cookies
Proporciona un metodo para obtener el username autenticado a partir de una Cookie*/
public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        //Obtiene las Cookies de la solicitud o un array vacio si no hay cookies
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //busca las cookies con el nombre username y devuelve su valor si esta presente
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
