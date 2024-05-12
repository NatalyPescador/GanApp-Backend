package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.dto.RegisterDto;
import com.proyectoGanApp.GanApp.dto.ResponseDto;
import com.proyectoGanApp.GanApp.model.ReviewEntity;
import com.proyectoGanApp.GanApp.model.TipoUsuario;
import com.proyectoGanApp.GanApp.model.UserEntity;
import com.proyectoGanApp.GanApp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<ReviewEntity> publishReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity review = ReviewEntity.builder()
                .resena(reviewEntity.getResena())
                .productoId("2")
                .usuarioId("2")
                .build();
        reviewRepository.save(review);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/reseñas/{id}")
    public ReviewEntity editReview(@PathVariable Long id, @RequestBody ReviewEntity editedReview) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setResena(editedReview.getResena());
                    return reviewRepository.save(review);
                }).orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    @DeleteMapping("/reseñas/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }
}



