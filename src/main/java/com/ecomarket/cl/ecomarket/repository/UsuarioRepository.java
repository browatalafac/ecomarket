package com.ecomarket.cl.ecomarket.repository;

import com.ecomarket.cl.ecomarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para encontrar un usuario por su correo electrónico
    Usuario findByCorreo(String correo);

    // Método para encontrar un usuario por su id (si no está ya por defecto)
    Optional<Usuario> findById(Long id);  // Asegúrate de que este método esté presente
}
