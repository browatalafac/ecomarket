package com.ecomarket.cl.ecomarket.repository;

import com.ecomarket.cl.ecomarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Usuario findByCorreo(String correo);


    Optional<Usuario> findById(Integer id);  // Asegúrate de que este método esté presente
}
