package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class CandidateTypeUpdateDto {
    private String candidateType;
    private boolean internal;
    private boolean active;
    private String city;
    private String teamType;
}
