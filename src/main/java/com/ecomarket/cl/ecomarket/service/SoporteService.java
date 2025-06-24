package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Soporte;
import com.ecomarket.cl.ecomarket.repository.SoporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> findAll() {
        return soporteRepository.findAll();
    }

    public Soporte findById(Integer id){
        return soporteRepository.findById(id).get();
    }

    public Soporte save(Soporte soporte){
        return soporteRepository.save(soporte);
    }

    public void delete(Integer id){
        soporteRepository.deleteById(id);
    }

}
