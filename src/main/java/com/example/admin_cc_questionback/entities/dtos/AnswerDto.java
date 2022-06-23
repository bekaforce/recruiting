package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class AnswerDto {
    private String content;
    private Long question_id;
    boolean correct;
}
