package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements  CrudRepository<Producto> {

    @Inject
    private EntityManager em;


    @Override
    public List<Producto> listar() throws Exception {
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {

        if (producto.getId() != null && producto.getId() > 0){
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Producto producto = porId(id);
        em.remove(producto);
    }
}
