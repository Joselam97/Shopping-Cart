package org.servlet.webapp.servlet.service;

import org.servlet.webapp.servlet.models.Usuario;
import org.servlet.webapp.servlet.repositories.UsuarioRepository;
import org.servlet.webapp.servlet.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try{
            return Optional.ofNullable(usuarioRepository.porId(id));
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try{
            usuarioRepository.guardar(usuario);
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            usuarioRepository.eliminar(id);
        }catch (SQLException e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }
}
