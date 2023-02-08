package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Message;

import java.util.List;

public interface MessageService {
    Message update(Message updatedMessage);
    Message messageById(Long id);
    String getText(Long id);
    List<Message> message();
    void saveCreatedMessageToLogs(String nameBefore, String nameNow, String textBefore, String textNow);
}
