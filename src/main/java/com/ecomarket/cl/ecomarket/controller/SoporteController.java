package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Soporte;

import com.ecomarket.cl.ecomarket.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public ResponseEntity<List<Soporte>> listar() {
        List<Soporte> soportes = soporteService.findAll();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    public ResponseEntity<Soporte> guardar(@RequestBody Soporte soporte){
        Soporte soporteNuevo = soporteService.save(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(soporteNuevo);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Soporte> buscar(@PathVariable Integer id){
        try{
            Soporte soporte  = soporteService.findById(id);
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try {
            soporteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}