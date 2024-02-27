package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
