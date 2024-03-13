package com.proyectoGanApp.GanApp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String nombreCompleto;
    String correo;
    String numeroTelefono;
    String password;
}
