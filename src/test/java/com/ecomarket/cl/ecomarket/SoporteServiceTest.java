package com.ecomarket.cl.ecomarket;

import com.ecomarket.cl.ecomarket.model.RolUsuario;
import com.ecomarket.cl.ecomarket.model.Soporte;
import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.repository.SoporteRepository;
import com.ecomarket.cl.ecomarket.service.SoporteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SoporteServiceTest {
    @Autowired
    private SoporteService soporteService;

    //Crea un MOCK del repositorio soporte para simular su comportamiento
    @MockitoBean
    private SoporteRepository soporteRepository;

    @Test
    public void testFindAll() {
        when(soporteRepository.findAll()).thenReturn(List.of(new Soporte(1,"No cumplia con las espectativas", "",
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE))));



        //LLAMA AL METODO findAll() del servicio
        List<Soporte> soportes = soporteService.findAll();

        //Verifica que la lista de vuelta no sea nula y contenga exactamente un soporte
        assertNotNull(soportes);
        assertEquals(1, soportes.size());
    }

    @Test
    public void testFindById(){
        Integer id = 1;

        Soporte soporte = new Soporte(1, "No cumplía con las expectativas", "",
                new Usuario(1, "1-9", "Ulises", "Torres", "01-01-2000", "elulises@gmail.com", "123456", RolUsuario.CLIENTE));

        soporteRepository.save(soporte); // Asegúrate de guardar el soporte si usas una BD en memoria para tests

        Optional<Soporte> foundOptional = soporteRepository.findById(id);
        assertTrue(foundOptional.isPresent());

        Soporte found = foundOptional.get();
        assertNotNull(found);
        assertEquals(id, found.getId());

    }


    @Test
    public void testSave(){



    }



}



