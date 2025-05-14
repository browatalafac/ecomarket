package com.ecomarket.cl.ecomarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "fechaPedido", nullable = false)
    private String fechaPedido;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private EstadoPedido estadoPedido;

    @Column (nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) // Aquí la clave foránea
    private Usuario usuario;

}
