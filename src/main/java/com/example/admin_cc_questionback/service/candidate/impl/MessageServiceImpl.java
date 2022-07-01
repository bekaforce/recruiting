package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Message;
import com.example.admin_cc_questionback.repository.candidate.MessageRepo;
import com.example.admin_cc_questionback.service.candidate.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    @Override
    public Message save(String text) {
        return messageRepo.save(new Message(text));
    }

    @Override
    public Message update(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public Message messageById(Long id) {
        return messageRepo.getMessageById(id);
    }

    @Override
    public String getText(Long id) {
        Message message = messageRepo.getMessageById(id);
        return message.getText();
    }

    @Override
    public List<Message> message() {
        return messageRepo.findAllOrderById();
    }
}
