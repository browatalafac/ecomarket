package com.ecomarket.cl.ecomarket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.ecomarket.cl.ecomarket.controller.FacturacionControllerV2;
import com.ecomarket.cl.ecomarket.controller.PedidoControllerV2;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{
    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).getPedidoByid(pedido.getId())).withSelfRel(),
                linkTo(methodOn(FacturacionControllerV2.class).getAllFacturas()).withRel("Pedidos"));
    }
}

