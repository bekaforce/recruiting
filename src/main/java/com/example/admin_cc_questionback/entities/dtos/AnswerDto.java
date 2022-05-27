package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class AnswerDto {
    private String content;
    private Long question_id;

    public AnswerDto(String content, Long question_id) {
        this.content = content;
        this.question_id = question_id;
    }
    public AnswerDto(){

    }
}
