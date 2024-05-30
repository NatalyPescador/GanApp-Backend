package com.proyectoGanApp.GanApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoGanApp.GanApp.model.ProductoEntity;
import com.proyectoGanApp.GanApp.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/GanApp")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    private final Path rootLocation = Paths.get("uploads");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @GetMapping("/producto")
    public List<ProductoEntity> listarProductos() {
        List<ProductoEntity> productos = productoRepository.findAll();
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/uploads/";
        for (ProductoEntity producto : productos) {
            producto.setImageUri(baseUrl + producto.getImageUri());
        }
        return productos;
    }

    @PostMapping(value = "/registrar-producto", consumes = "multipart/form-data")
    public ResponseEntity<?> crearProducto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("product") String productJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductoEntity producto = mapper.readValue(productJson, ProductoEntity.class);

        // Verifica que el archivo no esté vacío
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha proporcionado un archivo de imagen.");
        }

        // Lógica para guardar la imagen
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(filename).normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot store file outside current directory.");
        }

        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo.");
        }

        // Guarda solo el nombre del archivo en el producto
        producto.setImageUri(filename);

        // Depuración: verifica que el nombre de la imagen se haya asignado
        System.out.println("Nombre del archivo de imagen: " + producto.getImageUri());

        // Depuración: imprime los detalles del producto antes de guardar
        System.out.println("Detalles del producto antes de guardar: " + producto);

        // Guarda el producto en la base de datos
        ProductoEntity savedProduct = productoRepository.save(producto);

        // Depuración: verifica que el producto se haya guardado correctamente
        System.out.println("Producto guardado: " + savedProduct);

        return ResponseEntity.ok("Producto registrado con éxito");
    }
}