package org.servlet.webapp.servlet.service;

import jakarta.ejb.Local;
import org.servlet.webapp.servlet.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Local
public interface UsuarioService {
    Optional<Usuario> login(String username, String password);

    List<Usuario> listar();

    Optional<Usuario> porId(Long id);

    void guardar(Usuario usuario);

    void eliminar(Long id);
}
