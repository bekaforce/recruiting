package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.User;
import com.beeline.cc_question.services.GuestService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class GuestServiceImpl implements GuestService {
    private final MessageServiceImpl messageService;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public GuestServiceImpl(MessageServiceImpl messageService, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.messageService = messageService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addGuest(String name, String phoneNumber, String email) {
        LocalDateTime now = LocalDateTime.now();
        String password = String.valueOf(generateDigits());
        if (sendMessage(name, email, password)){
            String encoded = passwordEncoder.encode(password);
            return userService.register(email, phoneNumber, encoded, now);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean sendMessage(String name, String email, String password){
        return messageService.sendEmail(name, email, password);
    }

    @Override
    public int generateDigits() {
        int min = 1000;
        int max = 9999;
        return (int) (min + Math.random() * (max + min));
    }
}
