package com.proyectoGanApp.GanApp.dto;

import com.proyectoGanApp.GanApp.enums.MessageStatus;
import lombok.Data;

@Data
public class MessageRequestDto {

    private Long chatId;
    private Long senderId;
    private Long receiverId;
    private String message;
    private MessageStatus status;

}
