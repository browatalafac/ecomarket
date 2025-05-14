package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.EstadoPedido;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    // Obtener un pedido por su ID
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Actualizar el estado de un pedido
    public Pedido actualizarEstadoPedido(Long id, String estado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstadoPedido(EstadoPedido.valueOf(estado));  // Asumiendo que el estado es un enum
            return pedidoRepository.save(pedido);
        }
        return null;  // O lanzar una excepción
    }
    public void eliminarPedido(Long id){
        pedidoRepository.deleteById(id);
    }
}
