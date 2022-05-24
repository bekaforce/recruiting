package com.beeline.cc_question.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestDto {
    private String question;
    private String answer;
    @JsonIgnore
    private Long candidateId;

    public TestDto(String question, String answer, Long candidateId) {
        this.question = question;
        this.answer = answer;
        this.candidateId = candidateId;
    }

    public TestDto() {
    }
}
