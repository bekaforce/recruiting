package com.example.admin_cc_questionback.entities.dtos;


import lombok.Data;

@Data
public class QuestionDto {
    private String questionText;

    public QuestionDto(String questionText) {
        this.questionText = questionText;
    }

    public QuestionDto() {
    }
}
