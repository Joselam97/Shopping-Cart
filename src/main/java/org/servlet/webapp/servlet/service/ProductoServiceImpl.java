package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*Implementacion de ProductoService para gestionar productos y categorias
Actualmente, proporciona una lista fija de productos y metodos vacios para guardar y eliminar*/
public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",100000),
                new Producto(3L,"teclado mecanico","computacion",40000));
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return List.of();
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
