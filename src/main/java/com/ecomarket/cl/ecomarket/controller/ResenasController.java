package com.ecomarket.cl.ecomarket.controller;


import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Resenas;
import com.ecomarket.cl.ecomarket.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")

public class ResenasController {
    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resenas>> listar() {
        List<Resenas> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<Resenas> guardar(@RequestBody Resenas resenas){
        Resenas resenasNueva = resenaService.save(resenas);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenasNueva);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Resenas> buscar(@PathVariable Integer id){
        try{
            Resenas resenas  = resenaService.findById(id);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resenas> actualizar(@PathVariable Integer id, @RequestBody Resenas resenas){
        try{
            Resenas res= resenaService.findById(id);
            res.setId(id);
            res.setCalificacion(resenas.getCalificacion());
            res.setDetalleResena(resenas.getDetalleResena());
            resenaService.save(res);
            return ResponseEntity.ok(resenas);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            resenaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
