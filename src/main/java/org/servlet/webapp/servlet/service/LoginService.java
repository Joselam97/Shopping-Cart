package org.servlet.webapp.servlet.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

/*
Interfaz para el servicio de autenticación de usuario.
 * Proporciona métodos para obtener el nombre de usuario autenticado a partir de una solicitud HTTP.
 */
public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);

}
