package com.beeline.cc_question.services;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;

public interface GuestService {
    Candidate add(CandidateDto candidateDto);
    boolean sendMessage(String name, String email, String password);
    int generateDigits();
    Long setPassword(String password, Long id);
}
