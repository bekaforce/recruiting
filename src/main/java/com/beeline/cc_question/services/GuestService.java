package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.User;

public interface GuestService {
    User addGuest(String name, String phoneNumber, String email);
    boolean sendMessage(String name, String email, String password);
    int generateDigits();
}
