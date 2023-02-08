package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.candidate.MailMessage;

public interface EmailSenderService {
    boolean sendEmail(Candidate candidate, String introduction, String body);
    boolean sendMessage(MailMessage mailMessage);
    boolean sendCongratulationMessage(Candidate candidate);
}
