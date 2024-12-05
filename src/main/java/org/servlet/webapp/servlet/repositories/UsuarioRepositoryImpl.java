package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository // Marks this class as a repository for DI (Dependency Injection).
@RepositoryJdbc // Custom qualifier to specify the use of JDBC for database access.
public class UsuarioRepositoryImpl implements UsuarioRepository{

    // Injects the MySQL connection instance.
    @Inject
    @MysqlConn
    private Connection conn;

    // Maps a ResultSet row to a Usuario entity.
    private Usuario getUsuario(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }


    @Override
    public List<Usuario> listar() throws SQLException {
        // Retrieves all users from the database and returns them as a list.
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")){
            while (rs.next()) {
                // Maps each row to a Usuario object.
                Usuario p = getUsuario(rs);
                // Adds the Usuario object to the list.
                usuarios.add(p);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        // Finds a user by their ID and returns the corresponding Usuario object.
        Usuario usuario = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id=?")){
            // Sets the ID parameter for the query.
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    // Maps the row to a Usuario object.
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }


    @Override
    public void guardar(Usuario usuario) throws SQLException {
        // Saves or updates the user in the database depending on whether the ID is null or not.
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0){
            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO usuarios (username,password,email) VALUES (?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2,usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            // Sets the user ID if updating.
            if (usuario.getId() != null && usuario.getId() > 0){
                stmt.setLong(4,usuario.getId());
            }
            stmt.executeUpdate(); // Executes the insert or update query.
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // Deletes a user from the database by their ID.
        String sql = "DELETE FROM usuarios WHERE id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id); // Sets the ID for deletion.
            stmt.executeUpdate(); // Executes the delete query.
        }
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        // Finds a user by their username and returns the corresponding Usuario object.
        Usuario usuario = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")) {
            stmt.setString(1,username); // Sets the username parameter for the query.
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    // Creates a new Usuario object.
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                }
            }
        }
        return usuario;
    }
}
