package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.entities.Usuario;

// Interface for user-specific repository operations.
public interface UsuarioRepository extends CrudRepository<Usuario> {
    // Finds a user by their username.
    Usuario porUsername(String username) throws Exception;
}
