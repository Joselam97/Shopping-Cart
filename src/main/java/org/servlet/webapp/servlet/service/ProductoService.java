package org.servlet.webapp.servlet.service;

import jakarta.ejb.Local;
import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.List;
import java.util.Optional;


// Marks this interface as a local business interface in EJB, meaning it can be injected into other classes
@Local
public interface ProductoService {

    // Method to list all Producto entities.
    List<Producto> listar();

    // Method to find a Producto entity by its ID. Returns an Optional to safely handle cases where no Producto is found.
    Optional<Producto> findById(Long id);

    // Method to save or update a Producto entity.
    void guardar(Producto producto);

    // Method to delete a Producto entity by its ID.
    void eliminar(Long id);

    // Method to list all Categoria entities.
    List<Categoria> listarCategoria();

    // Method to find a Categoria entity by its ID. Returns an Optional to handle the potential absence of a Categoria
    Optional<Categoria> porIdCategoria(Long id);
}
