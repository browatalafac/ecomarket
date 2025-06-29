package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.assemblers.ResenasModelAssembler;
import com.ecomarket.cl.ecomarket.model.Resenas;
import com.ecomarket.cl.ecomarket.service.ResenaService;
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
@RequestMapping("/api/V2/resenas")
public class ResenasControllerV2 {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private ResenasModelAssembler assambler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Resenas>> getAllResenas() {
        List<EntityModel<Resenas>> resenas = resenaService.findAll().stream()
                .map(assambler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resenas,
                linkTo(methodOn(ResenasControllerV2.class).getAllResenas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Resenas> getResenasByid(@PathVariable Integer id) {
        Resenas resenas = resenaService.findById(id);
        return assambler.toModel(resenas);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resenas>> createResena(@RequestBody Resenas resenas) {
        Resenas newResena = resenaService.save(resenas);
        return ResponseEntity
                .created(linkTo(methodOn(ResenasControllerV2.class).getResenasByid(newResena.getId())).toUri())
                .body(assambler.toModel(newResena));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resenas>> updateResena(@PathVariable Integer id, @RequestBody Resenas resenas) {
        resenas.setId(id);
        Resenas updateResena = resenaService.save(resenas);
        return ResponseEntity
                .ok(assambler.toModel(updateResena));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteResena(@PathVariable Integer id) {
        resenaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
