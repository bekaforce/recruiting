package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class QuestionUpdateDto {
    private String questionText;
    private boolean key;
    private Long milliseconds;
}
