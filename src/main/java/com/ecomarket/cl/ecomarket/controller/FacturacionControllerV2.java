package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.assemblers.FacturacionModelAssembler;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.service.FacturacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/V2/factura")
public class FacturacionControllerV2 {

    @Autowired
    private FacturacionService facturacionService;

    @Autowired
    private FacturacionModelAssembler assambler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Facturacion>> getAllFacturas() {
        List<EntityModel<Facturacion>> facturacion = facturacionService.findAll().stream()
                .map(assambler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(facturacion,
                linkTo(methodOn(FacturacionControllerV2.class).getAllFacturas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Facturacion> getFacturaByid(@PathVariable Integer id) {
        Facturacion facturacion = facturacionService.findById(id);
        return assambler.toModel(facturacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Facturacion>> createFactura(@RequestBody Facturacion facturacion) {
        Facturacion newFactura = facturacionService.save(facturacion);
        return ResponseEntity
                .created(linkTo(methodOn(FacturacionControllerV2.class).getFacturaByid(newFactura.getId())).toUri())
                .body(assambler.toModel(newFactura));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Facturacion>> updateFactura(@PathVariable Integer id, @RequestBody Facturacion facturacion) {
        facturacion.setId(id);
        Facturacion updateFactura = facturacionService.save(facturacion);
        return ResponseEntity
                .ok(assambler.toModel(updateFactura));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteFactura(@PathVariable Long id) {
        facturacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
