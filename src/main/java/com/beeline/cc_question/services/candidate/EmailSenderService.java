package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.candidate.MailMessage;

import java.util.Map;

public interface EmailSenderService {
    boolean sendAuthEmail(String name, String email, String password);
    boolean sendMessage(MailMessage mailMessage);
    boolean sendEmail(String email, String introduction, String body, String templateId, String login);
    void sendConfirmationEmail(Candidate candidate);
    Map<String, Object> setParamMap(String introduction, String body, String login);
}
