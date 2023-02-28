package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateDto {
    private Long id;
    private String name;
    private String status;
    private String schedule;
    private LocalDateTime registration_Date;
}
