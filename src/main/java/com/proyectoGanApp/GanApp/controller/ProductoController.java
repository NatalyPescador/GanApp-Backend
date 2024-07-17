package com.proyectoGanApp.GanApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoGanApp.GanApp.model.ProductoEntity;
import com.proyectoGanApp.GanApp.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GanApp")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    private final Path rootLocation = Paths.get("uploads");

    @GetMapping("/producto")
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/producto/{id}")
    public Optional<ProductoEntity> getProducto(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @PostMapping(value = "/registrar-producto", consumes = "multipart/form-data")
    public ResponseEntity<?> crearProducto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("product") String productJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductoEntity producto = mapper.readValue(productJson, ProductoEntity.class);

        // Lógica para guardar la imagen
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(filename).normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot store file outside current directory.");
        }
        file.transferTo(destinationFile);

        // Guarda la ruta de la imagen en el producto
        producto.setImagen(destinationFile.toString());
        // Guarda el producto en la base de datos
        ProductoEntity savedProduct = productoRepository.save(producto);
        return ResponseEntity.ok("Producto registrado con éxito");

    }
}