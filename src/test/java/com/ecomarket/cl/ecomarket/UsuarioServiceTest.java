package com.ecomarket.cl.ecomarket;

import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.UsuarioRepository;
import com.ecomarket.cl.ecomarket.service.UsuarioService;
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
public class UsuarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Usuario usuario = new Usuario(1,"187849129","Ulises","Torres","16-01-2004","ulises@gmail.com","contrasena", RolUsuario.CLIENTE);
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Ulises", usuarios.get(0).getNombres());
    }

    @Test
    public void testFindById() {
        Usuario usuario = new Usuario(1, "187849129", "Ulises", "Torres", "16-01-2004", "ulises@gmail.com", "contrasena", RolUsuario.CLIENTE);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1);

        assertNotNull(result);
        assertEquals("Ulises", result.getNombres());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1,"187849129","Ulises","Torres","16-01-2004", "ulises@gmail.com","contrasena",RolUsuario.CLIENTE);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals("Ulises", saved.getNombres());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(usuarioRepository).deleteById(1);

        usuarioService.delete(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }
}
