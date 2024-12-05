package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Categoria;

import java.util.List;

// Marks this class as a JPA repository for dependency injection
@RepositoryJpa
@Repository
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {

    // Injects an EntityManager for interacting with the database using JPA
    @Inject
    private EntityManager em;

    // Method to list all categories from the database
    @Override
    public List<Categoria> listar() throws Exception {
        // Executes a JPQL query to fetch all Categoria entities
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    // Method to find a category by its ID
    @Override
    public Categoria porId(Long id) throws Exception {
        // Uses the EntityManager's find method to retrieve a Categoria by its primary key
        return em.find(Categoria.class, id);
    }

    // Method to save or update a Categoria entity
    @Override
    public void guardar(Categoria categoria) throws Exception {

        // If the Categoria has an ID (and it's greater than 0), it's an existing entity, so it is updated using merge
        if (categoria.getId() != null && categoria.getId() > 0) {
            em.merge(categoria);
        } else {
            // Otherwise, it's a new entity, so it is saved using persist
            em.persist(categoria);
        }
    }

    // Method to delete a category by its ID
    @Override
    public void eliminar(Long id) throws Exception {
        // Uses the EntityManager's remove method to delete the Categoria entity
        // The porId method is used to fetch the entity to ensure it's managed before removal
        em.remove(porId(id));
    }
}
