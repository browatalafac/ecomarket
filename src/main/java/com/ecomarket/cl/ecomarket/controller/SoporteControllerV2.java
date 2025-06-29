package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.assemblers.FacturacionModelAssembler;
import com.ecomarket.cl.ecomarket.assemblers.SoporteModelAssembler;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Soporte;
import com.ecomarket.cl.ecomarket.service.FacturacionService;
import com.ecomarket.cl.ecomarket.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/V2/soporte")
public class SoporteControllerV2 {

    @Autowired
    private SoporteService soporteService;

    @Autowired
    private SoporteModelAssembler assambler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Soporte>> getAllSoporte() {
        List<EntityModel<Soporte>> soporte = soporteService.findAll().stream()
                .map(assambler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(soporte,
                linkTo(methodOn(SoporteControllerV2.class).getAllSoporte()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Soporte> getSoporteByid(@PathVariable Integer id) {
        Soporte soporte = soporteService.findById(id);
        return assambler.toModel(soporte);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Soporte>> createSoporte(@RequestBody Soporte soporte) {
        Soporte newSoporte= soporteService.save(soporte);
        return ResponseEntity
                .created(linkTo(methodOn(SoporteControllerV2.class).getSoporteByid(newSoporte.getId())).toUri())
                .body(assambler.toModel(newSoporte));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Soporte>> updateSoporte(@PathVariable Integer id, @RequestBody Soporte soporte) {
        soporte.setId(id);
        Soporte updateSoporte = soporteService.save(soporte);
        return ResponseEntity
                .ok(assambler.toModel(updateSoporte));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSoporte(@PathVariable Integer id) {
        soporteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
