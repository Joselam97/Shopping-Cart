package org.servlet.webapp.servlet.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.servlet.webapp.servlet.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


// custom qualifier or annotation, used for dependency injection
@CarroCompra
public class Carro implements Serializable {

    // List to store items in the shopping cart
    private List<ItemCarro> items;

    // Logger injected for logging messages (transient means it won't be serialized)
    @Inject
    private transient Logger log;

    // PostConstruct annotation ensures this method is called after the constructor and dependency injection
    @PostConstruct
    public void inicializar(){
        // Initialize the list of items
        this.items = new ArrayList<>();
        log.info("Inicializando el Carro de Compras!"); // Log message when initializing the shopping cart
    }


    // PreDestroy annotation ensures this method is called before the object is destroyed
    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el Carro de Compras"); // Log message when destroying the shopping cart
    }


    // Method to add an item to the shopping cart, or increase its quantity if it already exists
    public void addItemCarro(ItemCarro itemCarro) {
        // Check if the item is already in the cart
        if (items.contains(itemCarro)) {
            // If the item exists, find it and increase its quantity by 1
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))  // Compare items by equality
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1); // Increment item quantity
            }
        } else {
            // If the item is not already in the cart, add it
            this.items.add(itemCarro);
        }
    }

    // Getter for the list of items in the shopping cart
    public List<ItemCarro> getItems() {
        return items;
    }


    // Method to calculate the total price of items in the cart by summing the amounts of each item
    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getImporte).sum(); // Sum the 'importe' of each item
    }


    // Method to remove a list of products based on their IDs
    public void removeProductos(List<String> productoIds){
        if (productoIds != null){
            productoIds.forEach(this::removeProducto); // Iterate and remove each product by ID
        }
    }


    // Method to remove a product from the cart by its ID
    public void removeProducto(String productoId){
        // Find the product using the ID and remove it if found
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }


    // Method to update the quantity of a product in the cart
    public void updateCantidad(String productoId, int cantidad){
        // Find the product and update its quantity if found
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }


    // Private helper method to find a product in the cart by its ID
    private Optional<ItemCarro> findProducto(String productoId){
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
