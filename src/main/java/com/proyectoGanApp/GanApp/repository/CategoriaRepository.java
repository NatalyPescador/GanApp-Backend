package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{

}
