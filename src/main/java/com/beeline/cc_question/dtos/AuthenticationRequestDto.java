package com.beeline.cc_question.dtos;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
