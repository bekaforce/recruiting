package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class QuestionInterviewDto {
    private String questionText;
    private boolean key;
    private Long candidateType_id;
    private Long seconds;
}
