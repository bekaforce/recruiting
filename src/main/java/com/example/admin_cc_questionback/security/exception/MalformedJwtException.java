package com.example.admin_cc_questionback.security.exception;

public class MalformedJwtException extends RuntimeException {
    public MalformedJwtException(String message) {
        super(message);
    }
}

