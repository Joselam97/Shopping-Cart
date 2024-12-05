package org.servlet.webapp.servlet.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.Service;
import org.servlet.webapp.servlet.models.entities.Usuario;
import org.servlet.webapp.servlet.repositories.RepositoryJpa;
import org.servlet.webapp.servlet.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;


/* @Service annotation indicates that this class is a service component
in the application, typically used for business logic. */
@Service
// used for business logic that can be invoked remotely or locally in EJB applications.
@Stateless
public class UsuarioServiceImpl implements UsuarioService{
    // Declare the repository interface to interact with the data layer.
    private UsuarioRepository usuarioRepository;


    // Constructor with @Inject annotation to perform dependency injection
    @Inject
    // The UsuarioRepository is injected into the service layer for data access.
    public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    /* Login method checks if a user with the provided username exists,
    and if the password matches. It returns an Optional<Usuario> to handle
    the possibility of no user being found or the password being incorrect. */
    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            // Fetch the user by username and filter by matching password
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception throwables) {
            // If an error occurs, throw a custom exception with the message and cause
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // List all users in the system by fetching them from the repository
    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (Exception e){
            // Catch any exception and throw a custom exception with the error details
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    // Find a user by their ID
    @Override
    public Optional<Usuario> porId(Long id) {
        try{
            return Optional.ofNullable(usuarioRepository.porId(id));
        }catch (Exception e){
            // If the user isn't found or another error occurs, throw a custom exception
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    // Save or update a user in the repository
    @Override
    public void guardar(Usuario usuario) {
        try{
            usuarioRepository.guardar(usuario);
        }catch (Exception e){
            // Handle any exceptions and wrap them in a custom exception
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    // Delete a user by their ID
    @Override
    public void eliminar(Long id) {
        try{
            usuarioRepository.eliminar(id);
        }catch (Exception e){
            // Catch any exception and throw a custom exception with the message and cause
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }
}
