package com.proyectoGanApp.GanApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDto {
    String newPassword;
    String confirmedPassword;
    String token;
}