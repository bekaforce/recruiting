package com.example.admin_cc_questionback.service;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.time.LocalDate;

public interface ParticipantService {
    void save(String name, String lastName, String birthday, String email, String address, String candidate_type, String status, String phone_number, String city, LocalDate invitation_date, String gender) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
