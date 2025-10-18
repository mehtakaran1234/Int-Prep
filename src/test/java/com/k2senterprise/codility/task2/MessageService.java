package com.k2senterprise.codility.task2;


import com.codility.audit.AuditException;
import com.codility.audit.DuplicatedAuditException;
import com.codility.message.Message;
import com.codility.message.MessageRepository;
import com.codility.notification.NotificationException;
import com.codility.notification.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final AuditService auditService;
    private final NotificationService notificationService;


    MessageService(MessageRepository messageRepository, AuditService auditService, NotificationService notificationService) {
        this.messageRepository = messageRepository;
        this.auditService = auditService;
        this.notificationService = notificationService;
    }

    @Transactional(noRollbackFor = NotificationException.class)
    public void sendAll(List<Message> messages) {

        for (Message message : messages) {

            messageRepository.save(message);


            auditService.collectAll(messages);
            notificationService.sendAll(messages);
        }
    }

    @Transactional(rollbackFor = {DuplicatedAuditException.class, Exception.class}, timeout = 10)
    public void send(Message message) throws DuplicatedAuditException {
        messageRepository.save(message);
        auditService.collect(message);
    }
}

