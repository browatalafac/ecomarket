package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.DTO.PedidoDTO;
import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.model.Pedido;
import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.service.FacturacionService;
import com.ecomarket.cl.ecomarket.service.PedidoService;
import com.ecomarket.cl.ecomarket.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/factura")
@Tag(name = "Facturas", description = "Operaciones relacionadas con facturas")
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Obtiene una lista de todas las facturas almacenadas")
    public ResponseEntity<List<Facturacion>> listar() {
        List<Facturacion> facturacions = facturacionService.findAll();
        if (facturacions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturacions);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva factura", description = "Guarda una nueva factura en la base de datos")
    public ResponseEntity<Facturacion> guardar(@RequestBody Facturacion facturacion){
        Facturacion facturaNueva = facturacionService.save(facturacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaNueva);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar factura por ID", description = "Obtiene una factura específica dado su ID")
    public ResponseEntity<Facturacion> buscar(@PathVariable Integer id){
        try{
            Facturacion facturacion  = facturacionService.findById(id);
            return ResponseEntity.ok(facturacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una factura", description = "Actualiza los datos de una factura existente dado su ID")
    public ResponseEntity<Facturacion> actualizar(@PathVariable Integer id, @RequestBody Facturacion facturacion){
        try{
            Facturacion fac= facturacionService.findById(id);
            fac.setId(id);
            fac.setNombreCompletoCliente(facturacion.getNombreCompletoCliente());
            fac.setDescripcionProductos(facturacion.getDescripcionProductos());
            fac.setPrecioFinalPedido(facturacion.getPrecioFinalPedido());
            facturacionService.save(fac);
            return ResponseEntity.ok(facturacion);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una factura", description = "Elimina una factura dado su ID")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            facturacionService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
