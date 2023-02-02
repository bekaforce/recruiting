package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.candidate.MailMessage;

public interface EmailSenderService {
    boolean sendAuthEmail(String name, String email, String password);
    String setMessageText(String name, String password, Long message_id);
    boolean sendMessage(MailMessage mailMessage);
    boolean sendConfirmationEmail(Candidate candidate);
}
