package com.beeline.cc_question.entities.dtos.candidate;

import lombok.Data;

@Data
public class CandidateTypeDto {
    private String candidateType;

    public CandidateTypeDto(String candidateType) {
        this.candidateType = candidateType;
    }
}
