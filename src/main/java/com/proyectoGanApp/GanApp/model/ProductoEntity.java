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
@Table(name = "Producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "imagen", nullable = false) // Nombre de la imagen
    private String imagen;

    @Column(name = "tipo_servicio_id", nullable = false)
    private Long tipoServicioId;

    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

}

