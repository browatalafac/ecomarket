package com.ecomarket.cl.ecomarket;

import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.FacturacionRepository;
import com.ecomarket.cl.ecomarket.service.FacturacionService;
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

@Service
@Transactional
@SpringBootTest
public class FacturacionServiceTest {
    @Autowired
    private FacturacionService facturacionService;

    //Crea un MOCK del repositorio facturacion para simular su comportamiento
    @MockitoBean
    private FacturacionRepository facturacionRepository;

    @Test
    public void testFindAll() {
        when(facturacionRepository.findAll()).thenReturn(
                List.of(new Facturacion(
                        1,
                        "Ulises Torres",
                        "Muy bueno",
                        10000.0,
                        new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE))
                )));

        //LLAMA AL METODO findAll() del servicio
        List<Facturacion> facturacions = facturacionService.findAll();

        //Verifica que la lista de vuelta no sea nula y contenga exactamente una facturacion
        assertNotNull(facturacions);
        assertEquals(1, facturacions.size());
    }
    @Test
    public void testFindById(){
        Integer id = 1;
        Facturacion facturacion = new Facturacion(id,"","",5000.0,  new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)));

        Facturacion found = facturacionService.findById(id);

        assertNotNull(facturacion);
        assertEquals(id, found.getId());

    }

    @Test
    public void testSave(){
        Facturacion facturacion = new Facturacion(1, "Ulises Torres", "Muy bueno", 5000.0,
                new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                        new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)));

        when(facturacionRepository.save(facturacion)).thenReturn(facturacion);

        Facturacion saved = facturacionService.save(facturacion);

        assertNotNull(saved);
        assertEquals("Ulises Torres", saved.getNombreCompletoCliente());



    }

    @Test
    public void testDeleteById(){
         Long id = 1L;

        //Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada
        doNothing().when(facturacionRepository).deleteById(id);

        facturacionService.delete(id);

        verify(facturacionRepository, times(1)).deleteById(id);
    }

}
