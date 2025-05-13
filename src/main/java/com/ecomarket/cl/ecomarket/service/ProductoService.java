package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.repository.ProductoRepository;
import com.ecomarket.cl.ecomarket.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(long id){
        return productoRepository.findById(id).get();
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }

}
