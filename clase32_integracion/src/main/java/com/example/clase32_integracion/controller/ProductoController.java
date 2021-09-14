package com.example.clase32_integracion.controller;

import com.example.clase32_integracion.persistance.entities.Producto;
import com.example.clase32_integracion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired(required = true)
    ProductoService service;

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody Producto p){
        ResponseEntity<String> respuesta = null;

        if(service.guardar(p) != null){
            respuesta = ResponseEntity.ok("El producto fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Error, no pudo agregarse el producto");
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> consultarTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/conStock")
    public ResponseEntity<List<Producto>> productosConStock(){
        return ResponseEntity.ok(service.buscarPorductosConStock());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok(service.eliminar(id));
    }

    @PutMapping()
    public ResponseEntity<String> actualizar(@RequestBody Producto p){
        return ResponseEntity.ok(service.actualizar(p));
    }
}
