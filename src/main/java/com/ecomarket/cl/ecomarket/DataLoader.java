package com.ecomarket.cl.ecomarket;
import com.ecomarket.cl.ecomarket.model.RolUsuario;
import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

    }


}