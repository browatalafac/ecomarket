package com.ecomarket.cl.ecomarket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.ecomarket.cl.ecomarket.controller.FacturacionControllerV2;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class FacturacionModelAssembler implements RepresentationModelAssembler<Facturacion, EntityModel<Facturacion>> {

    @Override
    public EntityModel<Facturacion> toModel(Facturacion facturacion) {
        return EntityModel.of(facturacion,
                linkTo(methodOn(FacturacionControllerV2.class).getFacturaByid(facturacion.getId())).withSelfRel(),
                linkTo(methodOn(FacturacionControllerV2.class).getAllFacturas()).withRel("Facturas"));
    }
}