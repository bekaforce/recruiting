package com.beeline.cc_question.entities.dtos.candidate;

import lombok.Data;

@Data
public class CandidateTypeDto {
    private String candidateType;
    private boolean schedule;

    public CandidateTypeDto(String candidateType, boolean schedule) {
        this.candidateType = candidateType;
        this.schedule = schedule;
    }
}
