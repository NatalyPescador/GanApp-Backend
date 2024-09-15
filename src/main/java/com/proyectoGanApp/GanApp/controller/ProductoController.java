package com.proyectoGanApp.GanApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoGanApp.GanApp.model.ProductoEntity;
import com.proyectoGanApp.GanApp.repository.ProductoRepository;
import com.proyectoGanApp.GanApp.service.S3Service;
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

    @Autowired
    private S3Service s3Service;

    private final Path rootLocation = Paths.get("uploads");

    @GetMapping("/producto")
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/producto/{id}")
    public Optional<ProductoEntity> getProducto(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    @GetMapping("/productos/{userId}")
    public List<ProductoEntity> getProductByUserId(@PathVariable Long userId) {
        return productoRepository.getProductsByUserId(userId);
    }

    @GetMapping("/productos/tipoServicio/{tipoServicioId}")
    public List<ProductoEntity> getProductosByTipoServicio(@PathVariable Long tipoServicioId) {
        return productoRepository.findByTipoServicioId(tipoServicioId);
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

        String imageUrl;
        try {
            imageUrl = s3Service.uploadFile(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }

        producto.setImagen(imageUrl);

        productoRepository.save(producto);

        return ResponseEntity.ok("Producto registrado con éxito. Imagen URL: " + imageUrl);

    }

    @PutMapping("/producto/actualizar/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody ProductoEntity updatedProduct) {
        productoRepository.findById(id)
                .map(product -> {
                    product.setPrecio(updatedProduct.getPrecio());
                    product.setDescripcion(updatedProduct.getDescripcion());
                    product.setRaza(updatedProduct.getRaza());
                    product.setUom(updatedProduct.getUom());
                    product.setEdad(updatedProduct.getEdad());
                    product.setCantidad(updatedProduct.getCantidad());
                    return productoRepository.save(product);
                });
        return ResponseEntity.ok("Información actualizada éxitosamente");

    }

    @DeleteMapping("/producto/borrar/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        productoRepository.findById(id)
                .map(product -> {
                    productoRepository.delete(product);
                    return ResponseEntity.ok().build();
                });
        return ResponseEntity.ok("Producto eliminado");
    }
}