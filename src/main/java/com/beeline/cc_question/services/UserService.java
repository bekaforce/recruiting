package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User register(String email, String phoneNumber, String password);
    User findByUsername(String userName);
    User findById(Long id);
    ResponseEntity<?> response(String username, String token);
}
