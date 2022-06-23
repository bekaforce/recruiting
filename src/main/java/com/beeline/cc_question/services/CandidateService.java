package com.beeline.cc_question.services;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;
import com.beeline.cc_question.entities.Questionnaire;

import java.time.LocalDateTime;
import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDto candidateDto);
    Candidate candidateById(Long id);
    List<Candidate> getAll();
    Candidate setStage(Candidate candidate, String stage);
    String getStage(Long candidate_id);
    Candidate candidateByEmail(String email);
    boolean expiration(LocalDateTime registration_date);
}
