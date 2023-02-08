package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class QuestionVideoDto {
    private String questionText;
    private Long candidateType_id;
    private Long seconds;
}
