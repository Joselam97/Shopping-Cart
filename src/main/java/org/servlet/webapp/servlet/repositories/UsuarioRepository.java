package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.Usuario;

import java.sql.SQLException;

//Interfaz para operaciones especificas en el repositorio Usuario
//Extiende las operaciones CRUD basicas de Repository a Usuario
public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
