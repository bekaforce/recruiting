package com.example.admin_cc_questionback.security.exception;

public class JwtExpirationException extends RuntimeException {
    public JwtExpirationException(String message) {
        super(message);
    }
}

