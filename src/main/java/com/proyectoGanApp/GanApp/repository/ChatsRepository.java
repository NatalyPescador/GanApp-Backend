package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.ChatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatsRepository extends JpaRepository<ChatsEntity, Long> {
    Optional<ChatsEntity> findByProductIdAndUserId(Long productId, Long userId);

    List<ChatsEntity> findByUserIdOrReceiverId(Long userId, Long receiverId);

    Optional<ChatsEntity> findByProductIdAndUserIdAndReceiverId(Long productId, Long userId, Long receiverId);

}
