package com.ecomarket.cl.ecomarket;
import com.ecomarket.cl.ecomarket.model.*;
import com.ecomarket.cl.ecomarket.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private FacturacionRepository facturacionRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ResenasRepository resenasRepository;
    @Autowired
    private SoporteRepository soporteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar tipos de usuarios

        for (int i = 0; i < 3; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setFechaNacimiento("24-dic-1999");
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setPassword("12356781234Aa!");
            usuario.setRol(faker.options().option(RolUsuario.class));

            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();
        //generar pedidos
        for (int i=0;i<3;i++){
            Pedido pedido= new Pedido();
            pedido.setFechaPedido("21-04-2025");
            pedido.setEstadoPedido(faker.options().option(EstadoPedido.class));
            pedido.setTotal(faker.number().randomDouble(1,10000,999999));
            Usuario usuarioAleatorio = faker.options().nextElement(usuarios);
            pedido.setUsuario(usuarioAleatorio);

            pedidoRepository.save(pedido);
        }

        List<Pedido> pedidos = pedidoRepository.findAll();
        // generar productos
        for (int i=0;i<3;i++){
            Producto producto= new Producto();
            producto.setNombre(faker.food().fruit());
            producto.setPrecio(faker.number().numberBetween(1000,100000));
            producto.setStock(faker.number().randomDigit());
            if (!pedidos.isEmpty()) {
                Pedido pedidoAleatorio = faker.options().nextElement(pedidos);
                producto.setPedido(pedidoAleatorio);
            }

            productoRepository.save(producto);
        }

        List<Producto> productos=productoRepository.findAll();
        //Generar reseñas
        for (int i=0;i<3;i++){
            Resenas resenas= new Resenas();
            resenas.setCalificacion(faker.number().numberBetween(1,5));
            resenas.setDetalleResena(faker.lorem().sentence());
            if (!productos.isEmpty()) {
                Producto productoAleatorio = faker.options().nextElement(productos);  // Selecciona un producto aleatorio
                resenas.setProducto(productoAleatorio);  // Asignar el producto aleatorio a la reseña
            }

            // Guardar la reseña en la base de datos
            resenasRepository.save(resenas);

        }

//      Generar facturación para cada pedido
        for (Pedido pedido : pedidos) {
            // Crear la factura
            Facturacion facturacion = new Facturacion();

            // Obtener el nombre del cliente desde el usuario asociado al pedido
            String nombreCliente = pedido.getUsuario().getNombres() + " " + pedido.getUsuario().getApellidos();
            facturacion.setNombreCompletoCliente(nombreCliente);

            // Calcular el precio final del pedido sumando el precio de los productos asociados
            double precioFinal = 0;
            for (Producto producto : pedido.getProductos()) {
                precioFinal += producto.getPrecio();
            }
            facturacion.setPrecioFinalPedido(precioFinal);

            // Descripción de los productos del pedido
            StringBuilder descripcionProductos = new StringBuilder();
            for (Producto producto : pedido.getProductos()) {
                descripcionProductos.append(producto.getNombre()).append(" - ");
            }
            facturacion.setDescripcionProductos(descripcionProductos.toString());

            // Asignar el pedido a la facturación
            facturacion.setPedido(pedido);

            // Guardar la facturación en la base de datos
            facturacionRepository.save(facturacion);
        }



    }


}