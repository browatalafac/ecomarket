package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name= "productos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private int precio;

    @Column (nullable = false)
    private int stock;





}
