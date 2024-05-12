package com.proyectoGanApp.GanApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    String nombreCompleto;
    String correo;
    String numeroTelefono;
    String password;
}
