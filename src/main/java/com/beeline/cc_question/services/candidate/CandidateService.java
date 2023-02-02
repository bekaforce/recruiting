package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDto candidateDto);
    Candidate candidateById(Long id);
    Candidate setStage(Candidate candidate, String stage);
    String checkCandidate(String name, String surname, LocalDate birthday);
    String getStage(Long candidate_id);
    Candidate candidateByEmail(String email);
    boolean expiration(LocalDateTime registration_date);
    Experience setExperience(Experience experience);
}
