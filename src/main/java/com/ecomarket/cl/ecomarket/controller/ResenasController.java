package com.ecomarket.cl.ecomarket.controller;


import com.ecomarket.cl.ecomarket.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resenas")

public class ResenasController {
    @Autowired
    private ResenaService resenaService;
}
