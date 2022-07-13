package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.MailMessage;
import com.beeline.cc_question.entities.candidate.Message;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;

public interface MessageService {
    Message messageById(Long id);
    String getText(Long id);
    boolean sendEmail(String name, String email, String password);
    String setMessageText(String name, String password, Long message_id);
    boolean sendMessage(MailMessage mailMessage);
    String setSuccessText(String name, String candidate_type, Long message_id);
    SuccessDto success(String name, String candidate_type);
}
