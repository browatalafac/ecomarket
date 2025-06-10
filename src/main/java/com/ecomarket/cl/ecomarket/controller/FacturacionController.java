package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.DTO.PedidoDTO;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/factura")
public class FacturacionController {
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

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos(){
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidos()
                .stream()
                .map(pedido -> new PedidoDTO(pedido))
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id){
        Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
        return pedidoOpt
                .map(pedido -> new ResponseEntity<>(new PedidoDTO(pedido), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstadoPedido(@PathVariable Long id, @RequestParam String estado){
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, estado.toUpperCase());
            if (pedidoActualizado != null){
                return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }   catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //Estado no valido, osea que no existe.
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
        if (pedidoOpt.isPresent()){
            pedidoService.eliminarPedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
