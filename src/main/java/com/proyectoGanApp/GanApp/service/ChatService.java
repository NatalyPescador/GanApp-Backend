package com.proyectoGanApp.GanApp.service;

import com.proyectoGanApp.GanApp.dto.ChatRequestDTO;
import com.proyectoGanApp.GanApp.dto.MessageRequestDto;
import com.proyectoGanApp.GanApp.enums.MessageStatus;
import com.proyectoGanApp.GanApp.model.ChatsEntity;
import com.proyectoGanApp.GanApp.model.MessagesEntity;
import com.proyectoGanApp.GanApp.repository.ChatsRepository;
import com.proyectoGanApp.GanApp.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    public ChatsEntity createChat(ChatRequestDTO chatRequest) {
        logger.debug("Creando chat con ProductId: {} y SenderId: {}", chatRequest.getProductId(), chatRequest.getUserId());

        Optional<ChatsEntity> existingChat = chatsRepository.findByProductIdAndUserId(chatRequest.getProductId(), chatRequest.getUserId());

        if (existingChat.isPresent()) {
            throw new IllegalStateException("Chat ya existe en base de datos, con el producto: " + chatRequest.getProductId() + " y el usuario: " + chatRequest.getUserId());
        }

        ChatsEntity chat = new ChatsEntity();
        chat.setProductId(chatRequest.getProductId());
        chat.setUserId(chatRequest.getUserId());
        chat.setReceiverId(chatRequest.getReceiverId());
        ChatsEntity savedChat = chatsRepository.save(chat);
        logger.debug("Chat creado: {}", savedChat);
        return savedChat;
    }

    public MessagesEntity sendMessage(MessageRequestDto messageRequest) {
        MessagesEntity msg = new MessagesEntity();
        msg.setChatId(messageRequest.getChatId());
        msg.setSenderId(messageRequest.getSenderId());
        msg.setReceiverId(messageRequest.getReceiverId());
        msg.setMessage(messageRequest.getMessage());
        msg.setStatus(MessageStatus.SENT);
        return messagesRepository.save(msg);
    }

    public List<MessagesEntity> getMessagesByChatId(Long chatId) {
        return messagesRepository.findByChatId(chatId);
    }

    public List<ChatsEntity> getChatsByUserId(Long userId) {
        logger.debug("Buscando chats para el userId: {}", userId);
        try {
            return chatsRepository.findByUserIdOrReceiverId(userId, userId);
        } catch (Exception e) {
            logger.error("Error al buscar chats para el userId: {}", userId, e);
            throw e;
        }
    }
}
