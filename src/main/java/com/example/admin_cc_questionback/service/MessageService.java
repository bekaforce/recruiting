package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Message;

public interface MessageService {
    Message createMessage(String text);
    Message updateMessage(Long id, String text);
    Message getMessage(Long id);
    String getText(Long id);
}
