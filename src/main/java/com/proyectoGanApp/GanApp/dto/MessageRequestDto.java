package com.proyectoGanApp.GanApp.dto;

import com.proyectoGanApp.GanApp.enums.MessageStatus;
import lombok.Data;

@Data
public class MessageRequestDto {

    private Long chatId;
    private String message;
    private Long senderId;
    private MessageStatus status;

}
