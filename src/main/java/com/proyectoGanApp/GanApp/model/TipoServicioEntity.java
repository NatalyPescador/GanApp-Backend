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
@Table(name = "TipoServicio")
public class TipoServicioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoServicioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}

