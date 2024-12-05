package org.servlet.webapp.servlet.repositories;

import java.util.List;

// Generic interface for CRUD operations on any entity type T.
public interface CrudRepository<T> {

    // Retrieves all entities of type T.
    List<T> listar() throws Exception;

    // Retrieves an entity of type T by its ID.
    T porId(Long id) throws Exception;

    // Saves or updates an entity of type T.
    void guardar(T t) throws Exception;

    // Deletes an entity of type T by its ID.
    void eliminar(Long id) throws Exception;
}
