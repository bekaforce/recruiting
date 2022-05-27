package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Message;
import com.example.admin_cc_questionback.repository.MessageRepo;
import com.example.admin_cc_questionback.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    @Override
    public Message createMessage(String text) {
        return messageRepo.save(new Message(text));
    }

    @Override
    public Message updateMessage(Long id, String text) {
        Message message = messageRepo.getMessageById(id);
        message.setText(text);
        return messageRepo.save(message);
    }

    @Override
    public Message getMessage(Long id) {
        return messageRepo.getMessageById(id);
    }

    @Override
    public String getText(Long id) {
        Message message = messageRepo.getMessageById(id);
        return message.getText();
    }
}
