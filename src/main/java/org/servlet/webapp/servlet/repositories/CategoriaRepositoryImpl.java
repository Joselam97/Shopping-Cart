package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.MysqlConn;
import org.servlet.webapp.servlet.configs.Repository;
import org.servlet.webapp.servlet.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection conn;

    @Inject
    public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categorias")){
            while(rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }


    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria= null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
    }

    @Override
    public void eliminar(Long id) throws SQLException {
    }


    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }
}
