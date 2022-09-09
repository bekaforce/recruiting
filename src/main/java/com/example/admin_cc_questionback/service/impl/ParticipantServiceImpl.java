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
    public void save(String name, String lastName, LocalDate birthday, String email, String address, String candidate_type, String status, String phone_number, String city, LocalDate invitation_date, String gender) {
        if (status.equals(ParticipantStatus.INVITED)){
            Participant participant = new Participant();
            participant.setName(name);
            participant.setLastName(lastName);
            participant.setBirthday(birthday);
            participant.setEmail(email);
            participant.setAddress(address);
            participant.setCandidate_type(candidate_type);
            participant.setStatus("ACTIVE");
            participant.setPhone_number(phone_number);
            participant.setCity(city);
            participant.setInvitation_date(invitation_date);
            participant.setGender(gender.toLowerCase().equals("мужчина"));
            participantRepo.save(participant);
        }
    }
}
