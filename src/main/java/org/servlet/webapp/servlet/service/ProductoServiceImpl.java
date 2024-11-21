package org.servlet.webapp.servlet.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.configs.Service;
import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;
import org.servlet.webapp.servlet.repositories.CrudRepository;
import org.servlet.webapp.servlet.repositories.RepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> repositoryJDBC;

    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> repositoryCategoriaJDBC;


    @Override
    public List<Producto> listar() {
        try {
            return repositoryJDBC.listar();
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJDBC.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJDBC.guardar(producto);
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJDBC.eliminar(id);
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJDBC.listar();
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJDBC.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }
}
