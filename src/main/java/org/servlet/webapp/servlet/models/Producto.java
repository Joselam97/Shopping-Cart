package org.servlet.webapp.servlet.models;

import java.time.LocalDate;

//clase que representa un producto en el sistema con las siguientes parametros
public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private int precio;
    private String sku;
    private LocalDate fechaRegistro;

    public Producto() {
    }

    //Constructor para el producto que contiene los siguientes argumentos
    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
    }

    //obtiene el id del producto
    public Long getId() {
        return id;
    }

    //establece el id del producto
    public void setId(Long id) {
        this.id = id;
    }

    //obtiene el nombre
    public String getNombre() {
        return nombre;
    }

    //establece el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    //establece la fecha de registro utilizando LocalDate para crear la fecha
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
