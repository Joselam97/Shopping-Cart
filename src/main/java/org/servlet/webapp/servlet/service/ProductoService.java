package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;

import java.util.List;
import java.util.Optional;

/*Interfaz para el servicio de gestion de productos y categorias
Proporciona metodos para realizar operaciones CRUD en productos y consultar categorias*/
public interface ProductoService {
    //Lista los productos disponibles
    List<Producto> listar();

    //Busca productos por su Id
    Optional<Producto> findById(Long id);

    //Guarda productos en el sistema
    void guardar(Producto producto);

    //Elimina productos por medio de su ID
    void eliminar(Long id);

    //lista las categorias de productos por su ID
    List<Categoria> listarCategoria();

    //Busca una categoria por su ID
    Optional<Categoria> porIdCategoria(Long id);
}
