package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.List;
import java.util.Optional;


public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> findById(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
