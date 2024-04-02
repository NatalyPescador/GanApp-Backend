package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.model.ProductoEntity;
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
    public ResponseEntity<String> crearProducto(@RequestParam("nombre") String nombre,
                                                @RequestParam("precio") Integer precio,
                                                @RequestParam("descripcion") String descripcion,
                                                @RequestParam("tipoServicioId") Long tipoServicioId,
                                                @RequestParam("categoriaId") Long categoriaId,
                                                @RequestParam("usuarioId") Long usuarioId,
                                                @RequestParam("imagen") MultipartFile imagen) {
        try {
            ProductoEntity producto = new ProductoEntity();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);
            producto.setTipoServicioId(tipoServicioId);
            producto.setCategoriaId(categoriaId);
            producto.setUsuarioId(usuarioId);

            // Guardar la imagen
            byte[] imagenBytes = imagen.getBytes();
            // Aquí puedes guardar la imagen en tu sistema de archivos o en una base de datos según tus necesidades
            // Por ahora, simplemente almacenaremos el nombre de la imagen
            producto.setImagen(imagen.getOriginalFilename());

            productoRepository.save(producto);

            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al procesar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}