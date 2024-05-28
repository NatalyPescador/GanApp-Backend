package com.proyectoGanApp.GanApp.controller;

import com.proyectoGanApp.GanApp.dto.ChatRequestDTO;
import com.proyectoGanApp.GanApp.dto.MessageRequestDto;
import com.proyectoGanApp.GanApp.model.ChatsEntity;
import com.proyectoGanApp.GanApp.model.MessagesEntity;
import com.proyectoGanApp.GanApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public ChatsEntity createChat(@RequestBody ChatRequestDTO request) {
        return chatService.createChat(request.getProductId());
    }

    @PostMapping("/send")
    public MessagesEntity sendMessage(@RequestBody MessageRequestDto request) {
        return chatService.sendMessage(request.getChatId(), request.getSenderId(), request.getReceiverId(), request.getMessage());
    }

    @GetMapping("/messages/{chatId}")
    public List<MessagesEntity> getMessages(@PathVariable Long chatId) {
        return chatService.getMessagesByChatId(chatId);
    }

    @GetMapping("/user/{userId}/chats")
    public List<ChatsEntity> getChatsByUserId(@PathVariable Long userId) {
        return chatService.getChatsByUserId(userId);
    }

}
