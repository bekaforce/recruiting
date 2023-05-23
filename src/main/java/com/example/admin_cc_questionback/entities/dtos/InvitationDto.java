package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InvitationDto {
    private String status;
    private LocalDateTime invitationDate;
    private String gender;
    private String invitationLocation;
}
