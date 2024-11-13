package org.servlet.webapp.servlet.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.ProductoServicePrincipal;
import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.repositories.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
@ProductoServicePrincipal
public class ProductoServiceJDBCImpl implements ProductoService {

    @Inject
    private Repository<Producto> repositoryJDBC;
    @Inject
    private Repository<Categoria> repositoryCategoriaJDBC;


    @Override
    public List<Producto> listar() {
        try {
            return repositoryJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJDBC.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJDBC.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJDBC.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJDBC.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }
}
