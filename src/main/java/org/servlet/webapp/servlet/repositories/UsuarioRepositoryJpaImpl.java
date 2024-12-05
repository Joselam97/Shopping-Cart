package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Usuario;

import java.util.List;

@RepositoryJpa // Custom annotation indicating that this repository uses JPA for data persistence.
@Repository // Marks the class as a repository for dependency injection.
public class UsuarioRepositoryJpaImpl implements UsuarioRepository{

    // Injects the EntityManager, which is used for managing entities in JPA.
    @Inject
    private EntityManager em;


    @Override
    public Usuario porUsername(String username) throws Exception {
        // Fetches a single user by their username from the database using JPA's query language.
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username) // Sets the 'username' parameter in the query.
                .getSingleResult(); // Executes the query and returns a single result
    }

    @Override
    public List<Usuario> listar() throws Exception {
        // Retrieves a list of all users from the database using a simple JPA query.
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class) // Creates the query to fetch all users.
                .getResultList(); // Executes the query and returns the result as a list of Usuario objects
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        // Retrieves a user by their ID from the database using JPA's find method.
        return em.find(Usuario.class, id); // Uses the EntityManager's 'find' method to fetch the user by their primary key.
    }

    @Override
    // Saves or updates the user in the database, depending on whether the user already exists.
    public void guardar(Usuario usuario) throws Exception {

        if (usuario.getId() != null && usuario.getId() > 0) {
            // Updates an existing user in the database.
            em.merge(usuario);
        } else {
            // Inserts a new user into the database.
            em.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        // Deletes a user by their ID from the database.
        em.remove(porId(id)); // Finds the user by their ID and removes them from the database.
    }
}
