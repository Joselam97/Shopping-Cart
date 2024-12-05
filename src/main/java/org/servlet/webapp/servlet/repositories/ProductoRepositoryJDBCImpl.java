package org.servlet.webapp.servlet.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.entities.Categoria;
import org.servlet.webapp.servlet.models.entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository // Indicates that this class is a repository bean in the application context.
@RepositoryJdbc // Custom annotation to signify it uses JDBC for persistence.
public class ProductoRepositoryJDBCImpl implements CrudRepository<Producto> {

    // Injects a logger instance for logging purposes.
    @Inject
    private Logger log;

    // Injects a database connection configured with @MysqlConn.
    @Inject
    @MysqlConn
    private Connection conn;

    // Method called after dependency injection to initialize the bean.
    @PostConstruct
    public void inicializar(){
        log.info("Inicializando el Beans " + this.getClass().getName());
    }

    // Method called before bean destruction for cleanup
    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el Beans " + this.getClass().getName());
    }

    @Override
    public List<Producto> listar() throws SQLException {
        // Initialize a list to store products.
        List<Producto> productos = new ArrayList<>();

        // SQL query to fetch products with categories.
        String sql = "SELECT p.*, c.nombre as categorias FROM productos as p "
                + "INNER JOIN categorias as c ON (p.categoria_id = c.id) ORDER BY p.id ASC";

        // Execute query and iterate results.
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = getProducto(rs); // Maps ResultSet row to Producto object
                productos.add(p); // Adds the Producto object to the list.
            }
        } catch (SQLException e) {
            // Logs any SQL exceptions.
            System.err.println("Error en listar productos: " + e.getMessage());
            // Rethrows the exception to be handled by the caller.
            throw e;
        }

        return productos;
    }


    @Override
    public Producto porId(Long id) throws SQLException {
        // Initialize the product as null.
        Producto producto = null;

        // SQL query to fetch a product by ID.
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categorias FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1,id);

            // Execute query and check if a result exists.
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    // Map the ResultSet to a Producto object
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }


    @Override
    public void guardar(Producto producto) throws SQLException {

        // Define the SQL query based on whether the product exists.
        String sql;
        // Check if the product has an ID (update case).
        if (producto.getId() != null && producto.getId() > 0) {
            // Update query for existing product.
            sql = "UPDATE productos SET nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos (nombre,precio,sku,categoria_id,fecha_registro) VALUES(?,?,?,?,?)"; // Insert query for a new product.
        }
        // Prepare the statement for execution.
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5,producto.getId());
            } else {
                stmt.setDate(5,Date.valueOf(producto.getFechaRegistro()));
            }
            // Execute the insert or update query.
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // SQL to delete a product by ID.
        String sql = "DELETE FROM productos WHERE id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            // Executes the deletion query.
            stmt.executeUpdate();
        }
    }


    private static Producto getProducto(ResultSet rs) throws SQLException {
        // Creates a new Producto object.
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        // Converts SQL date to LocalDate.
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        // Creates a new Categoria object.
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categorias"));
        // Associates the category with the product.
        p.setCategoria(c);
        return p;
    }
}
