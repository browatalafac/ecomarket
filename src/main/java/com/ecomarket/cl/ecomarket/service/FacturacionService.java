package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Facturacion;
import com.ecomarket.cl.ecomarket.repository.FacturacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FacturacionService {
    @Autowired
    private FacturacionRepository facturacionRepository;

    public List<Facturacion> findAll() {
        return facturacionRepository.findAll();
    }

    public Facturacion findById(long id){
        return facturacionRepository.findById(id).get();
    }

    public Facturacion save(Facturacion facturacion){
        return facturacionRepository.save(facturacion);
    }

    public void delete(Long id){
        facturacionRepository.deleteById(id);
    }

}
