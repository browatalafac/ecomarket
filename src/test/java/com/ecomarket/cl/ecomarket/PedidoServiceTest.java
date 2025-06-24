package com.ecomarket.cl.ecomarket;

import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.FacturacionRepository;
import com.ecomarket.cl.ecomarket.repository.PedidoRepository;
import com.ecomarket.cl.ecomarket.repository.UsuarioRepository;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Service
@Transactional
@SpringBootTest
public class PedidoServiceTest {
    @Autowired
    private PedidoService pedidoService;

    @Autowired PedidoRepository pedidoRepository;

    @MockitoBean
    private FacturacionRepository facturacionRepository;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Usuario usuario = new Usuario(1, "187849129","Ulises","Torres","16-01-2004","ulises@gmail.com","contrasena", RolUsuario.CLIENTE);
        Pedido pedido = new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0, usuario);
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.obtenerPedidos();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindById() {
        Usuario usuario = new Usuario(1,"187849129","Ulises","Torres","16-01-2004","ulises@gmail.com","contrasena",RolUsuario.CLIENTE);
        Pedido pedido = new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0, usuario);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> encontrado = pedidoService.obtenerPedidoPorId(1L);

        assertTrue(encontrado.isPresent());
        assertEquals(EstadoPedido.PENDIENTE, encontrado.get().getEstadoPedido());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1,"187849129","Ulises","Torres","16-01-2004","ulises@gmail.com","contrasena",RolUsuario.CLIENTE);
        Pedido pedido = new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0, usuario);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido creado = pedidoService.crearPedido(pedido);

        assertNotNull(creado);
        assertEquals(EstadoPedido.PENDIENTE, creado.getEstadoPedido());
        assertEquals(usuario, creado.getUsuario());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(pedidoRepository).deleteById(1L);
        pedidoService.eliminarPedido(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
