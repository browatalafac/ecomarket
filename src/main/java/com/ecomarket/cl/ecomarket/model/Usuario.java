package com.ecomarket.cl.ecomarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name= "usuarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (unique = true, length = 13 , nullable = false)
    private String run;

    @Column (nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = true)
    private String fechaNacimiento;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;


}


