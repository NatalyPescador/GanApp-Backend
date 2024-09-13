package com.proyectoGanApp.GanApp.repository;

import com.proyectoGanApp.GanApp.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{

    @Query("SELECT p FROM ProductoEntity p WHERE p.tipoServicioId = :tipoServicioId")
    List<ProductoEntity> findByTipoServicioId(Long tipoServicioId);

    @Query("SELECT p FROM ProductoEntity p WHERE p.usuarioId = :userId")
    List<ProductoEntity> getProductsByUserId(Long userId);

}
