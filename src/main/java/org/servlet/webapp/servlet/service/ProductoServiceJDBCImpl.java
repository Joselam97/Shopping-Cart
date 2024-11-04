package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;
import org.servlet.webapp.servlet.repositories.CategoriaRepositoryImpl;
import org.servlet.webapp.servlet.repositories.ProductoRepositoryJDBCImpl;
import org.servlet.webapp.servlet.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*Implementacion del servicio de productos y categorias basadas en JDBC
Proporciona metodos para realizar operaciones CRUD en productos y categorias
utilizando repositorios especificos para cada entidad*/
public class ProductoServiceJDBCImpl implements ProductoService {

    //Repositorio para operaciones de productos
    private Repository<Producto> repositoryJDBC;
    //Repositorio para operaciones de categorias
    private Repository<Categoria> repositoryCategoriaJDBC;


    //Constructor que inicializa los repositorios con una conexion a la base de datos
    public ProductoServiceJDBCImpl(Connection connection) {
        this.repositoryJDBC = new ProductoRepositoryJDBCImpl(connection);
        this.repositoryCategoriaJDBC = new CategoriaRepositoryImpl(connection);
    }

    //Lista todos los productos en la base de datos
    @Override
    public List<Producto> listar() {
        try {
            return repositoryJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Busca un producto por su id
    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJDBC.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Guarda un producto en la base de datos. Puede ser una operacion de insercion o actualizacion
    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJDBC.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Elimina un producto de la base de datos por su ID
    @Override
    public void eliminar(Long id) {
        try {
            repositoryJDBC.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Lista todas las categorias en la base de datos
    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Busca una categoria por su ID
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJDBC.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }
}
