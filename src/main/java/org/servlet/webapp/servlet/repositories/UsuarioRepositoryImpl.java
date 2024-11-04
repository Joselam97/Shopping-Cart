package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*Implementacion dek repositorio para el Usuario
Proporciona metodos para realizar operaciones CRUD basicas al usuario
 */
public class UsuarioRepositoryImpl implements UsuarioRepository{

    //Conexion a la base de datos
    private Connection conn;

    //Metodo auxiliar para ubicar un ResultSet a un Usuario, rs guarda datos del Usuario
    private Usuario getUsuario(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }

    //Constructor que recibe una conexion a la base de datos
    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    //Lista todos los usuarios almacenados en la base de datos
    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        //Query SQL para obtener todos los usuarios
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")){
            //Itera sobre el resultado y agrega cada usuario a la lista
            while (rs.next()) {
                Usuario p = getUsuario(rs);
                usuarios.add(p);
            }
        }
        return usuarios;
    }

    //Busca un usuario en la base de datos por su ID
    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        //Query SQL para buscar el usuario por su ID
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id=?")){
            stmt.setLong(1,id);
            //Ejecuta la consulta y asigna el resultado al objeto usuario
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    /*Guarda un usuario en la base de datos. Si ya tiene un ID entonces lo actualiza
    de lo contrario inserta un nuevo registro*/
    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        //Determina si se va a actualizar o insertar el usuario
        if (usuario.getId() != null && usuario.getId() > 0){
            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO usuarios (username,password,email) VALUES (?,?,?)";
        }
        //Ejecuta la actualizacion o insercion del usuario en la base de datos
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2,usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            if (usuario.getId() != null && usuario.getId() > 0){
                stmt.setLong(4,usuario.getId());
            }
            stmt.executeUpdate();
        }
    }

    //Elimina un usuario de la base de datos por su ID
    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        //Ejecuta la eliminacion del usuario en la base de datos
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    //Busca un usuario en la base de datos por su nombre de usuario
    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        //Query SQL para buscar el usuario por su username
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")) {
            stmt.setString(1,username);
            //Ejecuta la query y asigna el resultado al objeto usuario
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                }
            }
        }
        //devuelve el usuario
        return usuario;
    }
}
