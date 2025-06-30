package com.ecomarket.cl.ecomarket;

import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.ProductoRepository;
import com.ecomarket.cl.ecomarket.service.ProductoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Service
@Transactional
@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    //Crea un MOCK del repositorio producto para simular su comportamiento
    @MockitoBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        when(productoRepository.findAll()).thenReturn(
                List.of(new Producto(1,"Escoba", 4000, 100, new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                        new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)) )));

        //LLAMA AL METODO findAll() del servicio
        List<Producto> productos = productoService.findAll();

        //Verifica que la lista de vuelta no sea nula y contenga exactamente un producto
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindById(){
        Integer id = 1;
        Producto producto = new Producto(1,"Escoba", 4000, 100, new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)));

        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Producto found = productoService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave(){
        Producto producto = new Producto(1,"Escoba", 4000, 100, new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)));


        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.save(producto);

        assertNotNull(saved);
        assertEquals("Escoba", saved.getNombre());
    }

    @Test
    public void testDeleteById(){
        Integer id = 1;

        //Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada
        doNothing().when(productoRepository).deleteById(id);

        productoService.delete(id);

        verify(productoRepository, times(1)).deleteById(id);
    }
}
