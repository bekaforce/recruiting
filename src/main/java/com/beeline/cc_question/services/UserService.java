package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User register(String email, String phoneNumber, String password, LocalDateTime time);
    List<User> getAll();
    User findByUsername(String userName);
    User findById(Long id);
}
