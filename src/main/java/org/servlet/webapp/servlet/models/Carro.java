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


@CarroCompra
public class Carro implements Serializable {
    private List<ItemCarro> items;

    @Inject
    private transient Logger log;


    @PostConstruct
    public void inicializar(){
        this.items = new ArrayList<>();
        log.info("Inicializando el Carro de Compras!");
    }


    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el Carro de Compras");
    }


    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        } else {
            this.items.add(itemCarro);
        }
    }


    public List<ItemCarro> getItems() {
        return items;
    }



    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }



    public void removeProductos(List<String> productoIds){
        if (productoIds != null){
            productoIds.forEach(this::removeProducto);
        }
    }



    public void removeProducto(String productoId){
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }


    public void updateCantidad(String productoId, int cantidad){
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }



    private Optional<ItemCarro> findProducto(String productoId){
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
