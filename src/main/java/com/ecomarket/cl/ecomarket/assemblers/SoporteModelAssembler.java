package com.ecomarket.cl.ecomarket.assemblers;

import com.ecomarket.cl.ecomarket.controller.FacturacionControllerV2;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Soporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SoporteModelAssembler implements RepresentationModelAssembler<Soporte, EntityModel<Soporte>> {
    @Override
    public EntityModel<Soporte> toModel(Soporte soporte) {
        return EntityModel.of(soporte,
                linkTo(methodOn(FacturacionControllerV2.class).getFacturaByid(soporte.getId())).withSelfRel(),
                linkTo(methodOn(FacturacionControllerV2.class).getAllFacturas()).withRel("Soporte"));
    }
}
