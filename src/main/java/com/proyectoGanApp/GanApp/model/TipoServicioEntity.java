package com.proyectoGanApp.GanApp.model;

import com.proyectoGanApp.GanApp.repository.TipoServicioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TipoServicio")
public abstract class TipoServicioEntity implements TipoServicioRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoServicioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}

