package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//implementacion del repositorio para la categoria
//proporciona metodos para interactuar con la base de datos y realizar operaciones CRUD en cada categoria
public class CategoriaRepositoryImpl implements Repository<Categoria> {

    //conexion a la base de datos
    Connection conn;

    //constructor que reciba una conexion de la base de datos
    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    //Lista todas las categorias almacenadas en la base de datos
    //SQLException si ocurre algun error en la consulta
    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        //ejecuta la consulta para obtener todas las categorias
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categorias")){
            //itera sobre el resultado y agrega cada categoria a la lista
            while(rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }


    //busca una categoria en la base de datos por su ID
    //SQLException si ocurre algun error en la consulta
    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria= null;
        //prepara la consulta para buscar una categoria por su ID
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")) {
            stmt.setLong(1,id);
            //Ejecuta la consulta y asigna el resultado a la categoria
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    //Guarda una categoria en la base de datos
    @Override
    public void guardar(Categoria categoria) throws SQLException {
    }

    //Elimina una categoria en la base de datos
    @Override
    public void eliminar(Long id) throws SQLException {
    }


    //metodo para ubicar un ResultSet a una objeto categoria
    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        //establece un nombre y id para regresar la categoria
        return categoria;
    }
}
