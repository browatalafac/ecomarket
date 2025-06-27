package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Soporte;

import com.ecomarket.cl.ecomarket.service.SoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
@Tag(name = "Soporte", description = "Operaciones relacionadas con soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    @Operation(summary = "Listar todos los tickets de soporte", description = "Obtiene una lista de todos los tickets de soporte registrados")
    public ResponseEntity<List<Soporte>> listar() {
        List<Soporte> soportes = soporteService.findAll();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo ticket de soporte", description = "Guarda un nuevo ticket de soporte en la base de datos")
    public ResponseEntity<Soporte> guardar(@RequestBody Soporte soporte){
        Soporte soporteNuevo = soporteService.save(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(soporteNuevo);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar ticket de soporte por ID", description = "Obtiene un ticket de soporte específico dado su ID")
    public ResponseEntity<Soporte> buscar(@PathVariable Integer id){
        try{
            Soporte soporte  = soporteService.findById(id);
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ticket de soporte", description = "Modifica los datos de un ticket de soporte dado su ID")
    public ResponseEntity<Soporte> actualizar(@PathVariable Integer id, @RequestBody Soporte soporte){
        try{
            Soporte sop= soporteService.findById(id);
            sop.setId(id);
            sop.setUsuario(soporte.getUsuario());
            sop.setDescripcionProblema(soporte.getDescripcionProblema());
            sop.setDescripcionProblema(soporte.getRespuestaProblema());
            soporteService.save(sop);
            return ResponseEntity.ok(soporte);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ticket de soporte", description = "Elimina un ticket de soporte dado su ID")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try {
            soporteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}