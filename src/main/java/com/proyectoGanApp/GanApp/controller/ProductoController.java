package com.proyectoGanApp.GanApp.controller;


import com.proyectoGanApp.GanApp.model.ProductoEntity;
import com.proyectoGanApp.GanApp.repository.ProductoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/GanApp")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/producto")
    public List<ProductoEntity> listarProductos()
    { return productoRepository.findAll(); }

    @PostMapping("/producto")
    public ProductoEntity crearProducto(@RequestBody ProductoEntity producto){
        return productoRepository.save(producto);

    }
}
