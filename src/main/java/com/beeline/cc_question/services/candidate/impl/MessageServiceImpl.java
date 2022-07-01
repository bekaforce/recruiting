package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.MailMessage;
import com.beeline.cc_question.entities.candidate.Message;
import com.beeline.cc_question.repos.candidate.MessageRepo;
import com.beeline.cc_question.services.candidate.MessageService;
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
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    public final JavaMailSender emailSender;
    public final SpringTemplateEngine thymeleafTemplateEngine;
    Long introduction_id = 1L;
    Long body_id = 2L;

    public MessageServiceImpl(MessageRepo messageRepo, JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.messageRepo = messageRepo;
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public Message messageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    @Override
    public String getText(Long id) {
        Message message = messageById(id);
        if (message != null){
            return message.getText();
        }
        else {
            return null;
        }
    }

    @Override
    public boolean sendEmail(String name, String email, String password) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setTemplateId("email");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("introduction", setMessageText(name, password, introduction_id));
        paramMap.put("body", setMessageText(name, password, body_id));
        paramMap.put("login", "https://recruiting.beeline.kg/#/login?" + email); //https://recruiting.beeline.kg/#/login
        mailMessage.setParamMap(paramMap);
        mailMessage.setFrom("recruiting@beeline.kg");
        mailMessage.setTo(email);
        mailMessage.setSubject("Подтвердите авторизацию");
        return sendMessage(mailMessage);
    }

    @Override
    public String setMessageText(String name, String password, Long message_id) {
        String text = getText(message_id);
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


}
