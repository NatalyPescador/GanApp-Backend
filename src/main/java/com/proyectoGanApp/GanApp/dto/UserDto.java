package com.proyectoGanApp.GanApp.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String nombreCompleto;
    private String correo;
    private String numeroTelefono;

}
