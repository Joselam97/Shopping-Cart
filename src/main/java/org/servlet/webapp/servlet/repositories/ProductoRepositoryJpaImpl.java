package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.List;

// Marks this class as a JPA repository for dependency injection.
@RepositoryJpa
// Marks this class as a general repository.
@Repository
public class ProductoRepositoryJpaImpl implements  CrudRepository<Producto> {

    // Injects the EntityManager for database operations.
    @Inject
    private EntityManager em;


    @Override
    public List<Producto> listar() throws Exception {
        // Retrieves all Producto entities with their associated Categoria using JPQL
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        // Finds a Producto entity by its ID using EntityManager.
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {

        if (producto.getId() != null && producto.getId() > 0){
            // Updates an existing Producto entity.
            em.merge(producto);
        } else {
            // Saves a new Producto entity to the database.
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        // Fetches the Producto entity by ID.
        Producto producto = porId(id);
        // Removes the Producto entity from the database.
        em.remove(producto);
    }
}
