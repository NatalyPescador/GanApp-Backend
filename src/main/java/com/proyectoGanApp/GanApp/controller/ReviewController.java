package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.model.ReviewEntity;
import com.proyectoGanApp.GanApp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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



