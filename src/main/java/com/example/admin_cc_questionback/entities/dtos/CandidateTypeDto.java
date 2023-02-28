package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

@Data
public class CandidateTypeDto {
    private String candidateType;
    private boolean internal;
    private boolean active;
    private Long teamTypeId;
    private String city;
    private boolean schedule;
}
