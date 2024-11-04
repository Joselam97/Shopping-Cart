package org.servlet.webapp.servlet.repositories;

import org.servlet.webapp.servlet.models.Categoria;
import org.servlet.webapp.servlet.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Repositorio para el Producto
public class ProductoRepositoryJDBCImpl implements Repository<Producto>{

    //Conexion a la base de datos
    private Connection conn;

    //Constructor que reciba una conexion a la base de datos
    public ProductoRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    //Lista a todos los productos almacenados en la base de datos, incluyendo la categoria
    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        //Query SQL para obtener productos con sus categorias
        String sql = "SELECT p.*, c.nombre as categorias FROM productos as p "
                + "INNER JOIN categorias as c ON (p.categoria_id = c.id) ORDER BY p.id ASC";

        //Ejecuta la query y agrega cada producto a la lista
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en listar productos: " + e.getMessage());
            throw e;
        }

        return productos;
    }


    //Busca producto en la base de datos por su ID, incluyendo su categoria
    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        //Query SQL para buscar el producto por ID
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categorias FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1,id);

            //Ejecuta la consulta y asigna el resultado al objeto producto
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    /*Guarda un producto en la base de datos. Si el producto ya tiene un ID lo actualiza
    de lo contrario insert un nuevo registro
     */
    @Override
    public void guardar(Producto producto) throws SQLException {

        String sql;
        //determina si se va a actualizar o insertar el producto
        if (producto.getId() != null && producto.getId() > 0) {
            //acutaliiza los valores
            sql = "UPDATE productos SET nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        } else {
            //inserta valores a cada producto
            sql = "INSERT INTO productos (nombre,precio,sku,categoria_id,fecha_registro) VALUES(?,?,?,?,?)";
        }
        //ejecuta la actualizacion o insercion del producto en la base de datos
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5,producto.getId()); //para actualizacion
            } else {
                stmt.setDate(5,Date.valueOf(producto.getFechaRegistro())); //para insertarlo
            }
            stmt.executeUpdate();
        }
    }

    //Elimina un producto de la base de datos por su ID
    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        //ejecuta la eliminacion del producto en la base de datos
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }


    //Metodo auxiliar para ubicar un ResultSet a un Producto, incluyendo su categoria
    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        //Establece valores obtenidos a cada producto
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categorias"));
        p.setCategoria(c);
        return p;
    }
}
