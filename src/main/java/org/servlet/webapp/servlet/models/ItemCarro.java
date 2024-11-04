package org.servlet.webapp.servlet.models;

import java.util.Objects;

//Clase que representa un item del carro y cantidad de producto
public class ItemCarro {
    private int cantidad;
    private Producto producto;

    //constructor que inicializa el producto y su cantidad
    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    //obtiene la cantidad
    public int getCantidad() {
        return cantidad;
    }

    //establece la cantidad
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //obtiene el producto
    public Producto getProducto() {
        return producto;
    }

    //establece el producto
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /** verifica la igualdad entro dos objetos itemCarro
     * se consideran iguales si el ID es igual y su nombre tambien
     * @return true si los items son iguales, falso de no ser iguales
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) object;
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    //calcula el precio basado en la cantidad y producto
    public int getImporte() {
        return cantidad * producto.getPrecio();
    }
}
