package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.DTO.LoginRequest;
import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

   @Autowired
    private UsuarioService usuarioService;

   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

       Usuario usuario = usuarioService.login(loginRequest.getEmail(), loginRequest.getPassword());
       if (usuario != null) {
           // Inicio bueno
           return ResponseEntity.ok("Login exitoso. Bienvenido, " + usuario.getNombres());
       } else {
           // Datos incorrectos
           return ResponseEntity.status(401).body("Correo o contraseña incorrectos.");
       }
   }
}

