package com.beeline.cc_question.services.user;

import com.beeline.cc_question.entities.user.User;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.ResponseEntity;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface UserService {
    User register(String email, String phoneNumber, String password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    User findByUsername(String userName);
    User findById(Long id);
    ResponseEntity<?> response(String username, String token) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
