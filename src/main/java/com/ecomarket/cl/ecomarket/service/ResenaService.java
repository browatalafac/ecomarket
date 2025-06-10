package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.model.Resenas;
import com.ecomarket.cl.ecomarket.repository.ProductoRepository;
import com.ecomarket.cl.ecomarket.repository.ResenasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {
    @Autowired
    private ResenasRepository resenasRepository;

    public List<Resenas> findAll() {
        return resenasRepository.findAll();
    }

    public Resenas findById(long id){
        return resenasRepository.findById(id).get();}

    public Resenas save(Resenas resenas){
        return resenasRepository.save(resenas);
    }

    public void delete(Long id){
        resenasRepository.deleteById(id);
    }

}

