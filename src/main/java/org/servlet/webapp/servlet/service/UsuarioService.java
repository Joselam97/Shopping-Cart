package org.servlet.webapp.servlet.service;

import jakarta.ejb.Local;
import org.servlet.webapp.servlet.models.entities.Usuario;

import java.util.List;
import java.util.Optional;


/* Local annotation indicates that this interface is used for EJB (Enterprise JavaBeans)
and can be used locally within the application context */
@Local
public interface UsuarioService {

    /* Method to authenticate a user by username and password
    Returns an Optional<Usuario> to handle the possibility of no matching user found */
    Optional<Usuario> login(String username, String password);

    // Method to get the list of all users in the system
    List<Usuario> listar();

    // Method to find a user by their ID
    // Returns an Optional<Usuario> to handle the case where the user with the given ID doesn't exist
    Optional<Usuario> porId(Long id);

    // Method to save a new user or update an existing one in the system
    void guardar(Usuario usuario);

    // Method to delete a user based on their ID
    void eliminar(Long id);
}
