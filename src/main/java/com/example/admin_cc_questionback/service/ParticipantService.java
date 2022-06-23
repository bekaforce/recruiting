package com.example.admin_cc_questionback.service;


import java.time.LocalDate;

public interface ParticipantService {
    void save(String name, LocalDate birthday, String email, String address, String candidate_type, String status, String phone_number);
}
