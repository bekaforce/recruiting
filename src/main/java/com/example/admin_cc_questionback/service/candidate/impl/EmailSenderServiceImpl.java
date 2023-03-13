package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.candidate.MailMessage;
import com.example.admin_cc_questionback.service.candidate.EmailSenderService;
import org.apache.commons.codec.DecoderException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final MessageServiceImpl messageService;
    private final EncoderServiceImpl encoderService;

    public EmailSenderServiceImpl(JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine, MessageServiceImpl messageService, EncoderServiceImpl encoderService) {
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.messageService = messageService;
        this.encoderService = encoderService;
    }

    @Override
    public boolean sendEmail(Candidate candidate, String introduction, String body) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setTemplateId("email");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("introduction", introduction);
        paramMap.put("body", body);
        //paramMap.put("documents", "https://recruiting.beeline.kg/#/documents"); //https://recruiting.beeline.kg/#/login
        mailMessage.setParamMap(paramMap);
        mailMessage.setFrom("recruiting@beeline.kg");
        mailMessage.setTo(encoderService.decrypt(candidate.getEmail()));
        mailMessage.setSubject("Подтвердите авторизацию");
        return sendMessage(mailMessage);
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
    public boolean sendCongratulationMessage(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String introduction = messageService.getText(6L);
        String body = messageService.getText(7L);
        String name = encoderService.decrypt(candidate.getName());
        String candidateType = candidate.getCandidateType().getCandidateType();
        introduction = introduction.replaceAll("name", name);
        body = body.replaceAll("candidateType", candidateType);
        return sendEmail(candidate, introduction, body);
    }
}
