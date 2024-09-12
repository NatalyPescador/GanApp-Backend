package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.dto.ChatItemsDto;
import com.proyectoGanApp.GanApp.model.ChatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatsRepository extends JpaRepository<ChatsEntity, Long> {
    Optional<ChatsEntity> findByProductIdAndUserId(Long productId, Long userId);

    List<ChatsEntity> findByUserIdOrReceiverId(Long userId, Long receiverId);

    Optional<ChatsEntity> findByProductIdAndUserIdAndReceiverId(Long productId, Long userId, Long receiverId);

    @Query("SELECT new com.proyectoGanApp.GanApp.dto.ChatItemsDto(c.chatId, u.nombreCompleto, p.imagen) " +
            "FROM ChatsEntity c " +
            "JOIN UserEntity u ON (c.receiverId = u.userId OR c.userId = u.userId) " +
            "JOIN ProductoEntity p ON c.productId = p.productoId " +
            "WHERE c.userId = :userId OR c.receiverId = :userId")
    List<ChatItemsDto> findChatDetailsByUserId(Long userId);

}
