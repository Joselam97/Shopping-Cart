package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
