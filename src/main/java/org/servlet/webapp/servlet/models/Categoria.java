package org.servlet.webapp.servlet.models;

/**
 * clase que representa una categoria de productos en el sistema
 */
public class Categoria {
    //identificador unico de la categoria
    private Long id;
    //nombre de la categoria
    private String nombre;

    //Obtiene el ID de la categoria
    public Long getId() {
        return id;
    }

    //Obtiene el ID de la categoria
    public void setId(Long id) {
        this.id = id;
    }

    //Obtiene el nombre de la categoria
    public String getNombre() {
        return nombre;
    }

    //Establece el nombre de la categoria
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
