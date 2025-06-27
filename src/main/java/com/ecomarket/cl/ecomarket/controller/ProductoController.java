package com.ecomarket.cl.ecomarket.controller;
import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
        System.out.println("Si");
        Producto productoNuevo = productoService.save(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id){
        try{
            Producto producto  = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto){
        try{
            Producto pac= productoService.findById(id);
            pac.setId(id);
            pac.setNombre(producto.getNombre());
            pac.setPrecio(producto.getPrecio());
            pac.setStock(producto.getStock());
            productoService.save(pac);
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

