package org.servlet.webapp.servlet.service;

import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.Service;
import org.servlet.webapp.servlet.interceptors.TransactionalJpa;
import org.servlet.webapp.servlet.models.entities.Usuario;
import org.servlet.webapp.servlet.repositories.RepositoryJpa;
import org.servlet.webapp.servlet.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
@TransactionalJpa
//@TransactionalJDBC
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    @Inject
    //En caso de transicionar a @RepositoryJDBC seria dentro de (..)
    public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public List<Usuario> listar() {
        try {
            return usuarioRepository.listar();
        } catch (Exception e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try{
            return Optional.ofNullable(usuarioRepository.porId(id));
        }catch (Exception e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try{
            usuarioRepository.guardar(usuario);
        }catch (Exception e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            usuarioRepository.eliminar(id);
        }catch (Exception e){
            throw new ServiceJDBCException(e.getMessage(),e.getCause());
        }
    }
}
