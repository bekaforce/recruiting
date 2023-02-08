package com.example.admin_cc_questionback.entities.dtos;

import java.time.LocalDateTime;

public interface GetCandidateDto {
    Long getId();
    String getName();
    String getStatus();
    LocalDateTime getRegistration_Date();
}
