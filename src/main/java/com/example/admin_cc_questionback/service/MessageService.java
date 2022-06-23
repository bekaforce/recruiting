package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Message;

import java.util.List;

public interface MessageService {
    Message save(String text);
    Message update(Message message);
    Message messageById(Long id);
    String getText(Long id);
    List<Message> message();
}
