package com.proyectoGanApp.GanApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatItemsDto {

    public Long chatId;
    public Long userId;
    public String nombreUsuario;
    public String nombreReceiver;
    public String imagen;
}
