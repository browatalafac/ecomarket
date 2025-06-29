package com.ecomarket.cl.ecomarket.assemblers;

import com.ecomarket.cl.ecomarket.controller.FacturacionControllerV2;
import com.ecomarket.cl.ecomarket.controller.ProductoControllerV2;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getProductoByid(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAllProducto()).withRel("Productos"));
    }
}

