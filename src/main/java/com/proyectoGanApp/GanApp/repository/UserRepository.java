package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByCorreo(String correo);
}
