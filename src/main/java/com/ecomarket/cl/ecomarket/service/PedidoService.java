package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.EstadoPedido;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.repository.PedidoRepository;
import com.ecomarket.cl.ecomarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository){
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pedido crearPedido(Pedido pedido) {
        if (pedido.getUsuario() == null || pedido.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El pedido debe estar asociado a un usuario válido.");
        }

        Long usuarioId = Long.valueOf(pedido.getUsuario().getId());

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            // El usuario existe, puedes guardar el pedido
            return pedidoRepository.save(pedido);
        } else {
            // El usuario no existe, lanzas una excepción
            throw new IllegalArgumentException("El usuario con ID " + usuarioId + " no existe.");
        }
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
