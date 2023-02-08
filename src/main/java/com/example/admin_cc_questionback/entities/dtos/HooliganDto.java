package com.example.admin_cc_questionback.entities.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HooliganDto {
    private String name;
    private String surname;
    private LocalDate birthday;
    private String reason;
}
