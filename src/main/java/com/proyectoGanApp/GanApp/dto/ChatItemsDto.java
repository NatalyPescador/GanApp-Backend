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
    public String nombreCompleto;
    public String imagen;
}
