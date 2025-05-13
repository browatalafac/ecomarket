package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedidos")

public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    //Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(pedido);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        }  catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //En caso en error
        }
    }
}
