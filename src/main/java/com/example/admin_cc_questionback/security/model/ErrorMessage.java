package com.example.admin_cc_questionback.security.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorMessage {

    private String message;
    private String code;
    private String detail;

    public ErrorMessage(String message, String code) {
        this(message, code, "");
    }

    public ErrorMessage(String message) {
        this(message, "", "");
    }

}

