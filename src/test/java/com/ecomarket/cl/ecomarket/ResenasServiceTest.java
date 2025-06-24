package com.ecomarket.cl.ecomarket;
import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.ResenasRepository;
import com.ecomarket.cl.ecomarket.service.ResenaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest

public class ResenasServiceTest {

    @Autowired
    private ResenaService resenaService;

    //Crea un MOCK del repositorio resena para simular su comportamiento
    @MockitoBean
    private ResenasRepository resenasRepository;

    @Test
    public void testFindAll(){
        when(resenasRepository.findAll()).thenReturn(List.of(new Resenas(1,4,"Un producto ok", new Producto(1,"Escoba", 4000, 100, new Pedido(1L, "", EstadoPedido.PENDIENTE, 10000.0,
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE)) ))));

        //LLAMA AL METODO findAll() del servicio
        List<Resenas> resenas = resenaService.findAll();

        //Verifica que la lista de vuelta no sea nula y contenga exactamente una facturacion
        assertNotNull(resenas);
        assertEquals(1, resenas.size());

    }

    @Test
    public void testFindById(){


    }

}
