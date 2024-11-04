package org.servlet.webapp.servlet.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    //Lista que almacena los items en el carro de compras
    private List<ItemCarro> items;

    //constructor que inicializa la lista de items
    public Carro() {
        this.items = new ArrayList<>();
    }

    /**
     * agrega un item al carro de compras. si el item ya existe, incrementa su cantidad
     * @param itemCarro
     */
    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
            //busca el item existente para actualizar su cantidad
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        } else {
            //si el item no existe, lo agrega a la lista
            this.items.add(itemCarro);
        }
    }

    /**
     * obtiene la lista de items en el carro
     * @return lista de items del carro
     */
    public List<ItemCarro> getItems() {
        return items;
    }


    /**
     * calcula el total del carro sumando el importe de cada item
     * @return total del carro
     */
    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }


    /**
     * elimina varios productos del carro basandose en una lista de IDs de productos
     * @param productoIds Lista de IDs de productos a eliminar
     */
    public void removeProductos(List<String> productoIds){
        if (productoIds != null){
            productoIds.forEach(this::removeProducto);
        }
    }


    /**
     * elimina un producto del carro segun su ID
     * @param productoId ID del producto a eliminar
     */
    public void removeProducto(String productoId){
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    /**
     * actualiza la cantidad de un producto en el carro segun su ID
     * @param productoId ID del producto a actualizar
     * @param cantidad Nueva cantidad del producto
     */
    public void updateCantidad(String productoId, int cantidad){
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }


    /**
     * busca un producto en el carro segun su ID
     * @param productoId ID del producto a buscar
     * @return Optional que contiene el item si es encontrado, de lo contrario esta vacio
     */
    private Optional<ItemCarro> findProducto(String productoId){
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
