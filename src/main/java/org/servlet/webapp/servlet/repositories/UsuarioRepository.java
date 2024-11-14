package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws Exception;
}
