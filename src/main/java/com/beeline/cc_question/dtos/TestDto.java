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
    private boolean correct;
    private boolean key;

    public TestDto(String question, String answer, Long candidateId, boolean correct, boolean key) {
        this.question = question;
        this.answer = answer;
        this.candidateId = candidateId;
        this.correct = correct;
        this.key = key;
    }

    public TestDto() {
    }
}
