package com.ecomarket.cl.ecomarket.repository;

import com.ecomarket.cl.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

    public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    }

