package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
