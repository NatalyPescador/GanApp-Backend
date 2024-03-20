package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{

}
