package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.MailMessage;
import com.beeline.cc_question.entities.Message;

public interface MessageService {
    Message getMessage(Long id);
    String getText(Long id);
    boolean sendEmail(String name, String email, String password);
    String setMessageText(String name, String password, Long message_id);
    boolean sendMessage(MailMessage mailMessage);
}
