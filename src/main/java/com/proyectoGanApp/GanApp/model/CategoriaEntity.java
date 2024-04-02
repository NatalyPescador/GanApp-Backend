package com.proyectoGanApp.GanApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Categoria")
public class CategoriaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo_servicio_id", nullable = false)
    private Long tipoServicioId;



}