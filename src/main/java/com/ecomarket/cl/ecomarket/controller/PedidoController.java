package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.DTO.PedidoDTO;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Guarda un nuevo pedido en la base de datos")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(pedido);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        }  catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //En caso en error
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todos los pedidos existentes en el sistema")
    public ResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos(){
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidos()
                .stream()
                .map(pedido -> new PedidoDTO(pedido))
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por ID", description = "Busca y devuelve un pedido específico dado su ID")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id){
        Optional<Pedido> pedidoOpt = pedidoService.obtenerPedidoPorId(id);
        return pedidoOpt
                .map(pedido -> new ResponseEntity<>(new PedidoDTO(pedido), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar el estado de un pedido", description = "Modifica el estado de un pedido dado su ID y el nuevo estado")
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
    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido específico dado su ID")
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
