package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
public class ProductoServiceListImpl implements ProductoService{

    // List all Producto entities, returning a hardcoded list of products.
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",100000),
                new Producto(3L,"teclado mecanico","computacion",40000));
    }

    // Find a Producto by its ID. This method searches the list of products to find one with the matching ID.
    @Override
    public Optional<Producto> findById(Long id) {
        return listar().stream() // Convert the list of products to a stream for processing.
                .filter(p -> p.getId().equals(id)) // Filter the list by matching the ID.
                .findAny();  // Return an Optional containing the product if found, otherwise empty.
    }

    // Save a Producto. This method doesn't perform any action because it's an in-memory implementation.
    @Override
    public void guardar(Producto producto) {

    }

    // Delete a Producto by its ID. This method doesn't perform any action because it's an in-memory implementation.
    @Override
    public void eliminar(Long id) {

    }

    // List all Categoria entities. Returns an empty list as no categories are predefined in this implementation.
    @Override
    public List<Categoria> listarCategoria() {
        return List.of();
    }

    // Find a Categoria by its ID. Returns an empty Optional since no categories are predefined.
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
