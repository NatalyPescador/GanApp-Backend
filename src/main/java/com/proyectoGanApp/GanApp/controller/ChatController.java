package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.dto.ChatRequestDTO;
import com.proyectoGanApp.GanApp.dto.MessageRequestDto;
import com.proyectoGanApp.GanApp.model.ChatsEntity;
import com.proyectoGanApp.GanApp.model.MessagesEntity;
import com.proyectoGanApp.GanApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/GanApp")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@RequestBody ChatRequestDTO chatRequest) {
        logger.debug("Recibida solicitud para crear chat: {}", chatRequest);
        try {
            ChatsEntity createdChat = chatService.createChat(chatRequest);
            return ResponseEntity.ok(createdChat);
        } catch (IllegalStateException e) {
            logger.error("Error al crear el chat: {}", e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage()); // 409 Conflict
        }
    }

    @PostMapping("/send")
    public MessagesEntity sendMessage(@RequestBody MessageRequestDto messageRequest) {
        return chatService.sendMessage(messageRequest);
    }

    @GetMapping("/messages/{chatId}")
    public List<MessagesEntity> getMessages(@PathVariable Long chatId) {
        return chatService.getMessagesByChatId(chatId);
    }

    @GetMapping("/user/{userId}/chats")
    public ResponseEntity<?> getChatsByUserId(@PathVariable Long userId) {
        logger.debug("Recibida solicitud para obtener chats del usuario con userId: {}", userId);
        try {
            List<ChatsEntity> chats = chatService.getChatsByUserId(userId);
            return ResponseEntity.ok(chats);
        } catch (Exception e) {
            logger.error("Error al obtener los chats del usuario con userId: {}", userId, e);
            return ResponseEntity.status(500).body("Error al obtener los chats del usuario");
        }
    }

}
