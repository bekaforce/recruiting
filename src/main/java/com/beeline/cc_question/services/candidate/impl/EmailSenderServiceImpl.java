package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.candidate.MailMessage;
import com.beeline.cc_question.services.candidate.EmailSenderService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final MessageServiceImpl messageService;

    public EmailSenderServiceImpl(JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine, MessageServiceImpl messageService) {
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.messageService = messageService;
    }

    @Override
    public boolean sendMessage(MailMessage mailMessage) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(mailMessage.getFrom());
            messageHelper.setTo(mailMessage.getTo());
            messageHelper.setSubject(mailMessage.getSubject());
            Context context = new Context();
            context.setVariables(mailMessage.getParamMap());
            String content = thymeleafTemplateEngine.process(mailMessage.getTemplateId(), context);
            messageHelper.setText(content, true);
            emailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendEmail(String email, String introduction, String body, String templateId, String login) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setTemplateId(templateId);
        mailMessage.setParamMap(setParamMap(introduction, body, login));
        mailMessage.setFrom("recruiting@beeline.kg");
        mailMessage.setTo(email);
        mailMessage.setSubject("Подтверждение прохождения собеседования");
        return sendMessage(mailMessage);
    }

    @Override
    public Map<String, Object> setParamMap(String introduction, String body, String login) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("introduction", introduction);
        paramMap.put("body", body);
        paramMap.put("login", login);
        return paramMap;
    }

    @Override
    public void sendConfirmationEmail(Candidate candidate) {
        Long title_id = 3L;
        Long text_id = 4L;
        String introduction = messageService.setSuccessText(candidate.getName(), candidate.getCandidateType().getCandidateType(), title_id);
        String body = messageService.setSuccessText(candidate.getName(), candidate.getCandidateType().getCandidateType(), text_id);
        String templateId = "confirmation-email";
        sendEmail(candidate.getEmail(), introduction, body, templateId, "");
    }

    @Override
    public boolean sendAuthEmail(String name, String email, String password) {
        Long title_id = 1L;
        Long text_id = 2L;
        String introduction = messageService.setAuthEmailText(name, password, title_id);
        String body = messageService.setAuthEmailText(name, password, text_id);
        String login = "https://recruiting.beeline.kg/#/?email=" + email + "&login=true";
        String templateId = "email";
        return sendEmail(email, introduction, body, templateId, login);
    }
}
