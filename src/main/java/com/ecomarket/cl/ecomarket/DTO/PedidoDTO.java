package com.ecomarket.cl.ecomarket.DTO;

import com.ecomarket.cl.ecomarket.model.EstadoPedido;
import com.ecomarket.cl.ecomarket.model.Pedido;
import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String fechaPedido;
    private EstadoPedido estadoPedido;
    private Double total;

    public PedidoDTO(Pedido pedido){
        this.id = pedido.getId();
        this.fechaPedido = pedido.getFechaPedido();
        this.estadoPedido = pedido.getEstadoPedido();
        this.total = pedido.getTotal();
    }

}
