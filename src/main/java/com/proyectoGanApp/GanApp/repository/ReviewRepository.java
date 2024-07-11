package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.dto.ReviewDto;
import com.proyectoGanApp.GanApp.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT new com.proyectoGanApp.GanApp.dto.ReviewDto(r.usuarioId, r.resena) FROM ReviewEntity r WHERE r.productoId = :productId")
    List<ReviewDto> findReviewsByProductId(Long productId);
}
