package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Usuario;

import java.util.List;
import java.util.Optional;

/*Interfaz para el servicio de gestion de usuarios
proporciona metodos para realizar CRUD y autenticacion de usuarios*/
public interface UsuarioService {
    //Autentica un usuario en el sistema usando su nombre de usuario y password
    Optional<Usuario> login(String username, String password);

    //Lista a todos los usuarios registrados en el sistema
    List<Usuario> listar();

    //Busca un usuario en el sistema por su ID
    Optional<Usuario> porId(Long id);

    //Guarda un usuario en el sistema. Puede ser una operacion de insercion o actualizacion
    void guardar(Usuario usuario);

    //Elimina un usuario del sistema por su ID
    void eliminar(Long id);
}
