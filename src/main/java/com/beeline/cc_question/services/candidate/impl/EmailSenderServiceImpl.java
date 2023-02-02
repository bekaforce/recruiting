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
    Long introduction_id = 1L;
    Long body_id = 2L;

    public EmailSenderServiceImpl(JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine, MessageServiceImpl messageService) {
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.messageService = messageService;
    }

    @Override
    public boolean sendAuthEmail(String name, String email, String password) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setTemplateId("email");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("introduction", setMessageText(name, password, introduction_id));
        paramMap.put("body", setMessageText(name, password, body_id));
        paramMap.put("login", "https://recruiting.beeline.kg/#/?email=" + email + "&login=true"); //https://recruiting.beeline.kg/#/login
        mailMessage.setParamMap(paramMap);
        mailMessage.setFrom("recruiting@beeline.kg");
        mailMessage.setTo(email);
        mailMessage.setSubject("Подтвердите авторизацию");
        return sendMessage(mailMessage);
    }

    @Override
    public String setMessageText(String name, String password, Long message_id) {
        String text = messageService.getText(message_id);
        text = text.replaceAll("name", name);
        text = text.replaceAll("password", password);
        return text;
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
    public boolean sendConfirmationEmail(Candidate candidate) {

        return false;
    }
}
