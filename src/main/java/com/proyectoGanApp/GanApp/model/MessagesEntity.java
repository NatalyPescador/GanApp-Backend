package com.proyectoGanApp.GanApp.model;

import com.proyectoGanApp.GanApp.enums.MessageStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Messages")
public class MessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    @Column(name="chat_id", nullable = false)
    private Long chatId;

    @Column(name="message", nullable = false)
    private String message;

    @Column(name = "time_stamp", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    @Column(name = "senderId", nullable = false)
    private Long senderId;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private MessageStatus status;

    @PrePersist
    protected void onCreate() {
        timeStamp = LocalDateTime.now();
    }

}
