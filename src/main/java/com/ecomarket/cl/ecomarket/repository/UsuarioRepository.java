package com.ecomarket.cl.ecomarket.repository;

import com.ecomarket.cl.ecomarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para encontrar un usuario por su correo electrónico
    Usuario findByCorreo(String correo);
}
