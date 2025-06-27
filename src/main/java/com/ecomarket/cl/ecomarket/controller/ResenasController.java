package com.ecomarket.cl.ecomarket.controller;


import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Resenas;
import com.ecomarket.cl.ecomarket.service.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseñas", description = "Operaciones relacionadas con las reseñas de productos")
public class ResenasController {
    @Autowired
    private ResenaService resenaService;

    @GetMapping
    @Operation(summary = "Listar todas las reseñas", description = "Obtiene una lista de todas las reseñas registradas")
    public ResponseEntity<List<Resenas>> listar() {
        List<Resenas> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reseña", description = "Guarda una nueva reseña en la base de datos")
    public ResponseEntity<Resenas> guardar(@RequestBody Resenas resenas){
        Resenas resenasNueva = resenaService.save(resenas);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenasNueva);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar reseña por ID", description = "Obtiene una reseña específica dado su ID")
    public ResponseEntity<Resenas> buscar(@PathVariable Integer id){
        try{
            Resenas resenas  = resenaService.findById(id);
            return ResponseEntity.ok(resenas);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una reseña", description = "Modifica los datos de una reseña dado su ID")
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
    @Operation(summary = "Eliminar una reseña", description = "Elimina una reseña dado su ID")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try {
            resenaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
