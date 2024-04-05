package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.model.ProductoEntity;
import com.proyectoGanApp.GanApp.model.TipoServicioEntity;
import com.proyectoGanApp.GanApp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/GanApp")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/producto")
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/registrar-producto")
    public ProductoEntity crearProducto(@RequestBody ProductoEntity TipoProducto){
        return productoRepository.save(TipoProducto);
    }

}