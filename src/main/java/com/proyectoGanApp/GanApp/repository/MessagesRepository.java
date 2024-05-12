package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository <MessagesEntity, Long> {
}
