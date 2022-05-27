package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.Candidate;
import com.example.admin_cc_questionback.repository.CandidateRepo;
import com.example.admin_cc_questionback.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepo candidateRepo;

    public CandidateServiceImpl(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    @Override
    public Candidate candidateById(Long id) {
        return candidateRepo.findCandidateById(id);
    }

    @Override
    public List<CandidateDto> all(String vacancy) {
        return candidateRepo.all(vacancy);
    }

    @Override
    public List<CandidateDto> operators() {
        return all("Operator");
    }


}
