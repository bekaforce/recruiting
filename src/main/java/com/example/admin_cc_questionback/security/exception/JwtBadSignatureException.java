package com.example.admin_cc_questionback.security.exception;

public class JwtBadSignatureException extends RuntimeException {
    public JwtBadSignatureException(String message) {
        super(message);
    }
}

