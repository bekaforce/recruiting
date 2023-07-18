package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StageAnalyticsByDateDto {
    private Long candidateTypeId;
    private LocalDateTime start;
    private LocalDateTime end;

}
