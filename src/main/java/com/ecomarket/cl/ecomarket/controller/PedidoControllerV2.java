package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.assemblers.FacturacionModelAssembler;
import com.ecomarket.cl.ecomarket.assemblers.PedidoModelAssembler;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.service.FacturacionService;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/V2/pedido")
public class PedidoControllerV2 {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler assambler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pedido>> getAllPedido() {
        List<EntityModel<Pedido>> pedido = pedidoService.obtenerPedidos().stream()
                .map(assambler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).getAllPedido()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pedido> getPedidoByid(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
        return assambler.toModel(pedido);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> createPedido(@RequestBody Pedido pedido) {
        Pedido newPedido= pedidoService.crearPedido(pedido);
        return ResponseEntity
                .created(linkTo(methodOn(PedidoControllerV2.class).getPedidoByid(newPedido.getId())).toUri())
                .body(assambler.toModel(newPedido));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Facturacion>> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido updatePedido = pedidoService.actualizarEstadoPedido(pedido);
        return ResponseEntity
                .ok(assambler.toModel(updatePedido));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
}
