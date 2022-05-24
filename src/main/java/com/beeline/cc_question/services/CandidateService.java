package com.beeline.cc_question.services;

import com.beeline.cc_question.dtos.CandidateDto;
import com.beeline.cc_question.entities.Candidate;

public interface CandidateService {
    Candidate saveCandidate(CandidateDto candidateDto);
    Candidate getCandidateById(Long id);
}
