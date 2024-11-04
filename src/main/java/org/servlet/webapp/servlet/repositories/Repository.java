package org.servlet.webapp.servlet.repositories;

import java.sql.SQLException;
import java.util.List;

//Interfaz generica para operaciones CRUD en un repositorio
//<T> entidad generica que maneja el repositorio
public interface Repository<T> {
    //Lista todas las entidades almacenadas en el repositorio
    List<T> listar() throws SQLException;
    //Busca entidades por su ID
    T porId(Long id) throws SQLException;
    //Guarda entidades en el repositorio
    void guardar(T t) throws SQLException;
    //Elimina entidades del repositorio
    void eliminar(Long id) throws SQLException;
}
