package com.proyectoGanApp.GanApp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokenResponseDto {

    private final String token;
    private final String expirationTime;

}
