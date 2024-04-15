package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
    @Query(value = "SELECT c FROM CategoriaEntity c WHERE tipoServicioId = :typeServiceId ")
    public List<CategoriaEntity> getCategoryByServiceType(Long typeServiceId);
}
