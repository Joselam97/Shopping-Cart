package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RepositoryJdbc
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    // Connection object used to interact with the database
    private Connection conn;

    // Constructor-based dependency injection for the database connection
    @Inject
    public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    // Method to fetch all records from the 'categorias' table
    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>(); // List to hold retrieved categories


        // Using a Statement to execute a query
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categorias")){

            // Iterate over the result set and map each row to a Categoria object
            while(rs.next()) {
                Categoria categoria = getCategoria(rs); // Helper method to map a row to an object
                categorias.add(categoria); // Add the object to the list
            }
        }
        return categorias;
    }

    // Method to fetch a single category by its ID
    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria= null; // Initialize a Categoria object

        // Using a PreparedStatement to prevent SQL injection
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")) {
            stmt.setLong(1,id); // Set the parameter for the query

            // Execute the query and process the result set
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs); // Map the result to a Categoria object
                }
            }
        }
        return categoria;
    }

    // Placeholder method for saving a category (not implemented)
    @Override
    public void guardar(Categoria categoria) throws SQLException {
    }

    // Placeholder method for deleting a category by its ID
    @Override
    public void eliminar(Long id) throws SQLException {
    }


    // Helper method to map a ResultSet row to a Categoria object
    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria(); // Create a new Categoria instance

        // Set the properties of the Categoria object from the ResultSet
        categoria.setNombre(rs.getString("nombre")); // Map the 'nombre' column
        categoria.setId(rs.getLong("id")); // Map the 'id' column
        return categoria;
    }
}
