package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository <MessagesEntity, Long> {
    List<MessagesEntity> findByChatId(Long chatId);
    List<MessagesEntity> findAll();
}
