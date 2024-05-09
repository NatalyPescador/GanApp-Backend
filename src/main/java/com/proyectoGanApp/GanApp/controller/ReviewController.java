package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.auth.AuthResponse;
import com.proyectoGanApp.GanApp.auth.RegisterRequest;
import com.proyectoGanApp.GanApp.model.ReviewEntity;
import com.proyectoGanApp.GanApp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GanApp")
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reseñas")
    public List<ReviewEntity> getReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping("/reseñas")
    public ReviewEntity publishReview(@RequestBody ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

//    @PutMapping("actualizar/{id}")
//    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity upUser) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setNombre(upUser.getNombre());
//                    user.setNumIdentificacion(upUser.getNumIdentificacion());
//                    user.setDireccion(upUser.getDireccion());
//                    user.setTelefono(upUser.getTelefono());
//                    user.setCiudad(upUser.getCiudad());
//                    user.setEstado(upUser.getEstado());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
//    }
//
//    @DeleteMapping("/borrar/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    userRepository.delete(user);
//                    return ResponseEntity.ok().build();
//                })
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
//    }
}
