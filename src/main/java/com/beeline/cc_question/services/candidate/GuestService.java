package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;

public interface GuestService {
    Candidate add(CandidateDto candidateDto);
    boolean sendMessage(String name, String email, String password);
    int generateDigits();
    Long setPassword(String password, Long id);
}
