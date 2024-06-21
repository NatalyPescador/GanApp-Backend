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
@Table(name = "Resenas")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resenasId;

    @Column(name = "resenna", nullable = false, columnDefinition = "TEXT")
    private String resena;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId;
}
