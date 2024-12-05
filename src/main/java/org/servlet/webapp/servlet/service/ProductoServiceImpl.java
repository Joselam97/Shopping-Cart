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

// Custom annotation to mark the class as a service.
@Service
// Custom annotation to indicate this is the main service for Producto-related operations.
@ProductoServicePrincipal
// Marks the class as stateless, meaning it doesn't store client-specific state between method calls.
@Stateless
public class ProductoServiceImpl implements ProductoService {

    // Dependency injection of repositories for Producto and Categoria entities.
    @Inject
    @RepositoryJpa
    // Repository for managing Producto entities.
    private CrudRepository<Producto> repositoryJDBC;

    @Inject
    @RepositoryJpa
    // Repository for managing Categoria entities.
    private CrudRepository<Categoria> repositoryCategoriaJDBC;


    // List all Producto entities.
    @Override
    public List<Producto> listar() {
        try {
            // Retrieves all Producto entities from the repository.
            return repositoryJDBC.listar();
        } catch (Exception throwables) {
            // If an exception occurs, wrap it in a custom ServiceJDBCException with the message and cause.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // Find a Producto entity by its ID, wrapped in Optional to handle the case of missing entities.
    @Override
    public Optional<Producto> findById(Long id) {
        try {
            // Retrieves a Producto by ID and wraps it in Optional.
            return Optional.ofNullable(repositoryJDBC.porId(id));
        } catch (Exception throwables) {
            // Handle exceptions and throw a custom exception.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // Save or update a Producto entity.
    @Override
    public void guardar(Producto producto) {
        try {
            // Saves or updates the Producto entity in the repository.
            repositoryJDBC.guardar(producto);
        } catch (Exception throwables) {
            // Handle exceptions and throw a custom exception.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // Delete a Producto entity by its ID.
    @Override
    public void eliminar(Long id) {
        try {
            // Deletes the Producto entity from the repository.
            repositoryJDBC.eliminar(id);
        } catch (Exception throwables) {
            // Handle exceptions and throw a custom exception.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // List all Categoria entities.
    @Override
    public List<Categoria> listarCategoria() {
        try {
            // Retrieves all Categoria entities from the repository.
            return repositoryCategoriaJDBC.listar();
        } catch (Exception throwables) {
            // Handle exceptions and throw a custom exception.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    // Find a Categoria entity by its ID, wrapped in Optional to handle missing entities.
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            // Retrieves a Categoria by ID and wraps it in Optional.
            return Optional.ofNullable(repositoryCategoriaJDBC.porId(id));
        } catch (Exception throwables) {
            // Handle exceptions and throw a custom exception.
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }
}
