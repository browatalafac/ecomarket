package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.assemblers.ProductoModelAssembler;
import com.ecomarket.cl.ecomarket.assemblers.ProductoModelAssembler;
import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.service.ProductoService;
import com.ecomarket.cl.ecomarket.service.ProductoService;
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
@RequestMapping("/api/V2/producto")
public class ProductoControllerV2 {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assambler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProducto() {
        List<EntityModel<Producto>> producto = productoService.findAll().stream()
                .map(assambler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getAllProducto()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Producto> getProductoByid(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        return assambler.toModel(producto);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> createFactura(@RequestBody Producto producto) {
        Producto newProducto = productoService.save(producto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).getProductoByid(newProducto.getId())).toUri())
                .body(assambler.toModel(newProducto));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updateProducto = productoService.save(producto);
        return ResponseEntity
                .ok(assambler.toModel(updateProducto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
