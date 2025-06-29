package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name= "Soporte")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String descripcionProblema;

    @Column (nullable = false)
    private String respuestaProblema;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "soporte_id", nullable = false)
    private Usuario usuario;
}
