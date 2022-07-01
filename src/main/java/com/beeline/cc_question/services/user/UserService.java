package com.beeline.cc_question.services.user;

import com.beeline.cc_question.entities.user.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User register(String email, String phoneNumber, String password);
    User findByUsername(String userName);
    User findById(Long id);
    ResponseEntity<?> response(String username, String token);
}
