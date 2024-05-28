package com.proyectoGanApp.GanApp.service;

import com.proyectoGanApp.GanApp.enums.MessageStatus;
import com.proyectoGanApp.GanApp.model.ChatsEntity;
import com.proyectoGanApp.GanApp.model.MessagesEntity;
import com.proyectoGanApp.GanApp.repository.ChatsRepository;
import com.proyectoGanApp.GanApp.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private MessagesRepository messagesRepository;

    public ChatsEntity createChat(Long productId) {
        ChatsEntity chat = new ChatsEntity();
        chat.setProductId(productId);
        return chatsRepository.save(chat);
    }

    public MessagesEntity sendMessage(Long chatId, Long senderId, Long receiverId, String message) {
        MessagesEntity msg = new MessagesEntity();
        msg.setChatId(chatId);
        msg.setSenderId(senderId);
        msg.setReceiverId(receiverId);
        msg.setMessage(message);
        msg.setStatus(MessageStatus.SENT);
        return messagesRepository.save(msg);
    }

    public List<MessagesEntity> getMessagesByChatId(Long chatId) {
        return messagesRepository.findByChatId(chatId);
    }

    public List<ChatsEntity> getChatsByUserId(Long userId) {
        List<MessagesEntity> messages = messagesRepository.findAll();
        List<Long> chatIds = messages.stream()
                .filter(message -> message.getSenderId().equals(userId) || message.getReceiverId().equals(userId))
                .map(MessagesEntity::getChatId)
                .distinct()
                .collect(Collectors.toList());
        return chatsRepository.findAllById(chatIds);
    }

}
