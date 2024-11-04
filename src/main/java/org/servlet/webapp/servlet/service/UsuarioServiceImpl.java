package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.repositories.UsuarioRepository;
import org.servlet.webapp.servlet.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*Implementacion del servicio de gestion de usuarios
Proporciona metodos para realizar operaciones CRUD y autenticacion de usuarios
*/
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    //Constructor que inicializa el repositorio de usuarios con una conexion a la base de datos
    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    //Autentica al usuario en el sistema usando su username y password
    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            //Busca el usuario por username y password
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    //Lista todos los usuarios registrados en el sistema
    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    //Busca un usuario en el sistema por su ID
    @Override
    public Optional<Usuario> porId(Long id) {
        try{
            return Optional.ofNullable(usuarioRepository.porId(id));
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    //Guarda un usuario en el sistema. Puede ser una operacion de insercion o actualizacion
    @Override
    public void guardar(Usuario usuario) {
        try{
            usuarioRepository.guardar(usuario);
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    //Elimina un usuario del sistema por su ID
    @Override
    public void eliminar(Long id) {
        try{
            usuarioRepository.eliminar(id);
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }
}
