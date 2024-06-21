package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.model.ReviewEntity;
import com.proyectoGanApp.GanApp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/reseñas/{productId}")
    public List<ArrayList> getReviewsByProductId(@PathVariable Long productId) {
        return reviewRepository.findReviewsByProductId(productId);
    }

    @PostMapping("/reseñas")
    public ResponseEntity<ReviewEntity> publishReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity review = ReviewEntity.builder()
                .resena(reviewEntity.getResena())
                .productoId(reviewEntity.getProductoId())
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



