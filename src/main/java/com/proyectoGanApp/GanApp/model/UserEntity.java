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
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "Nombre_Completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "Correo", nullable = false)
    private String correo;

    @Column(name = "Numero_De_Celular", nullable = false)
    private String numeroTelefono;

    @Column(name = "password", nullable = false)
    private String password;

}
