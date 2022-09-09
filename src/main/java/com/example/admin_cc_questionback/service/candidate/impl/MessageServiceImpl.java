package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Message;
import com.example.admin_cc_questionback.repository.candidate.MessageRepo;
import com.example.admin_cc_questionback.service.candidate.MessageService;
import com.example.admin_cc_questionback.service.loggers.impl.MessageLoggerServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    private final MessageLoggerServiceImpl messageLoggerService;

    public MessageServiceImpl(MessageRepo messageRepo, MessageLoggerServiceImpl messageLoggerService) {
        this.messageRepo = messageRepo;
        this.messageLoggerService = messageLoggerService;
    }

    @Override
    public Message update(Message updatedMessage) {
        Message message = messageById(updatedMessage.getId());
        if (message != null){
            saveCreatedMessageToLogs(message.getName(), updatedMessage.getName(), message.getText(), updatedMessage.getText());
            message.setName(updatedMessage.getName());
            message.setText(updatedMessage.getText());
            return messageRepo.save(message);
        }
        return null;
    }

    @Override
    public Message messageById(Long id) {
        return messageRepo.getMessageById(id);
    }

    @Override
    public List<Message> message() {
        return messageRepo.findAllOrderById();
    }

    @Override
    public void saveCreatedMessageToLogs(String nameBefore, String nameNow, String textBefore, String textNow) {
        messageLoggerService.saveUpdate(nameBefore, nameNow, textBefore, textNow);
    }
}