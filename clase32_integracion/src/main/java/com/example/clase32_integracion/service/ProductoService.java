package com.example.clase32_integracion.service;

import com.example.clase32_integracion.controller.ProductoController;
import com.example.clase32_integracion.persistance.entities.Producto;
import com.example.clase32_integracion.persistance.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ServiceInterface<Producto> {
    @Autowired
    ProductoRepository repositorio;

    @Override
    public String guardar(Producto p){
        if(repositorio.save(p) != null){
            return "OK";
        }else{
            return null;
        }
    }

    @Override
    public List<Producto> obtenerTodos(){
        return repositorio.findAll();
    }

    @Override
    public String actualizar(Producto p) {
        String respuesta = "No se ha logrado actualizar el producto con id: " + p.getId() + " porque no se lo encontró en la base de datos";
        if(repositorio.findById(p.getId()).isPresent()){
            Producto productoModificado = this.buscarPorId(p.getId());
            productoModificado.setNombre(p.getNombre());
            productoModificado.setCantidad(p.getCantidad());
            productoModificado.setDescripcion(p.getDescripcion());
            repositorio.save(productoModificado);
            respuesta = "Actualización con éxito del producto con id: " + p.getId();
        }
        return respuesta;
    }

    @Override
    public String eliminar(Integer id) {
        String respuesta = null;
        if(repositorio.findById(id).isPresent()){
            repositorio.deleteById(id);
            respuesta = "Eliminado con éxito";
        }
        return respuesta;
    }

    @Override
    public Producto buscarPorId(Integer id){
        Producto productoObtenido = null;
        if(repositorio.findById(id).isPresent()){
            productoObtenido = repositorio.findById(id).get();
        }
        return productoObtenido;
    }


    public List<Producto> buscarPorductosConStock() {
        List<Producto> productos = this.obtenerTodos();
        List<Producto> productosConStock = new ArrayList<>();
        for (Producto p:productos){
            if (p.getCantidad()>0){
                productosConStock.add(p);
            }
        }
        return productosConStock;
    }


}
