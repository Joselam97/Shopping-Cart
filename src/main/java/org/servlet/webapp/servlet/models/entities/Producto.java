package org.servlet.webapp.servlet.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

// Marks the class as a JPA entity and maps it to the "productos" table in the database
@Entity
@Table(name = "productos")
public class Producto {

    // The ID of the product, which is the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generated using the identity strategy (auto-increment)
    private Long id;

    private String nombre;

    // Many-to-One relationship with Categoria (fetch type is lazy to load it when needed)
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    private int precio;
    private String sku;

    // The date when the product was registered in the system
    @Column(name = "fecha_registro") // Column name in the database is 'fecha_registro'
    private LocalDate fechaRegistro;


    // Default constructor (necessary for JPA)
    public Producto() {
    }

    // Parameterized constructor to initialize Producto with id, name, category type, and price
    public Producto(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        // Create a Categoria object and set its name based on the 'tipo' argument
        Categoria categoria = new Categoria();
        categoria.setNombre(tipo);
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getter and setter methods for all the fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

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
