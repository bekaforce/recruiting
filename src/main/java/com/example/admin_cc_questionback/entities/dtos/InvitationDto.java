package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvitationDto {
    private String status;
    private LocalDate invitationDate;
    private String gender;
}
