package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name= "facturacion")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Facturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "nombre_cliente")
    private String nombreCompletoCliente;

    @Column (nullable = false)
    private String descripcionProductos;

    @Column (nullable = false)
    private Double precioFinalPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facturacion_id", nullable = false)
    private Pedido pedido;
}
