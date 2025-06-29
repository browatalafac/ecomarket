package com.ecomarket.cl.ecomarket.assemblers;

import com.ecomarket.cl.ecomarket.controller.ResenasControllerV2;
import com.ecomarket.cl.ecomarket.model.Resenas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResenasModelAssembler implements RepresentationModelAssembler<Resenas, EntityModel<Resenas>> {

    @Override
    public EntityModel<Resenas> toModel(Resenas resenas) {
        return EntityModel.of(resenas,
                linkTo(methodOn(ResenasControllerV2.class).getResenasByid(resenas.getId())).withSelfRel(),
                linkTo(methodOn(ResenasControllerV2.class).getAllResenas()).withRel("Facturas"));
    }
}
