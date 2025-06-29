package com.ecomarket.cl.ecomarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name= "resenas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resenas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private Integer calificacion; //cambiar para que tenga un rango de 1-5

    @Column (nullable = true)
    private String detalleResena;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resena_id", nullable = false)
    private Producto producto;
}
