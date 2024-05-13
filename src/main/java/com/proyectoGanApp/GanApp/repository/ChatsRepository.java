package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.ChatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<ChatsEntity, Long> {
}
