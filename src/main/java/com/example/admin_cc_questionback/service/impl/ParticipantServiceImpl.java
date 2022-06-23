package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Participant;
import com.example.admin_cc_questionback.repository.ParticipantRepo;
import com.example.admin_cc_questionback.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepo participantRepo;

    public ParticipantServiceImpl(ParticipantRepo participantRepo) {
        this.participantRepo = participantRepo;
    }

    @Override
    public void save(String name, LocalDate birthday, String email, String address, String candidate_type, String status, String phone_number) {
        if (status.equals(ParticipantStatus.INVITED)){
            Participant participant = new Participant(name, birthday, email, address, candidate_type, "ACTIVE", phone_number);
            participantRepo.save(participant);
        }
    }
}
