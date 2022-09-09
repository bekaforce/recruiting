package com.example.admin_cc_questionback.service;


import java.time.LocalDate;

public interface ParticipantService {
    void save(String name, String lastName, LocalDate birthday, String email, String address, String candidate_type, String status, String phone_number, String city, LocalDate invitation_date, String gender);
}
