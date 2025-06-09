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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false) // Aquí la clave foránea
    private Pedido pedido;




}
