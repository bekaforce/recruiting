package com.example.admin_cc_questionback.entities.dtos;

import java.time.LocalDateTime;

public interface CandidateDto {
    Long getId();
    String getName();
    String getSurname();
    LocalDateTime getRegistration_Date();
}
