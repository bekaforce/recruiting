package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate candidateById(Long id);
    List<CandidateDto> all(String vacancy);
    List<CandidateDto> operators();
}
