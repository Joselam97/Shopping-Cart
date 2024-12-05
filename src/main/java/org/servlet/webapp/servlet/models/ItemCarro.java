package org.servlet.webapp.servlet.models;

import org.servlet.webapp.servlet.models.entities.Producto;

import java.util.Objects;

public class ItemCarro {

    // Quantity of the product in the shopping cart
    private int cantidad;
    // The product associated with this item
    private Producto producto;

    // Constructor to initialize the quantity and the product
    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Overriding the equals method to compare ItemCarro objects based on their product details (ID and name)
    @Override
    public boolean equals(Object object) {
        // Check if the objects are the same instance
        if (this == object) return true;
        // Check if the object is null or of a different class
        if (object == null || getClass() != object.getClass()) return false;

        // Cast the object to ItemCarro and compare product details (ID and name)
        ItemCarro itemCarro = (ItemCarro) object;
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    // Method to calculate the total price of this item in the cart (quantity * product price)
    public int getImporte() {
        return cantidad * producto.getPrecio();
    }
}
