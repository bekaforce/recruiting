package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.CandidateType;
import com.beeline.cc_question.entities.dtos.candidate.CandidateTypeDto;
import com.beeline.cc_question.repos.candidate.CandidateTypeRepo;
import com.beeline.cc_question.services.candidate.CandidateTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateTypeServiceImpl implements CandidateTypeService {
    private final CandidateTypeRepo candidateTypeRepo;

    public CandidateTypeServiceImpl(CandidateTypeRepo candidateTypeRepo) {
        this.candidateTypeRepo = candidateTypeRepo;
    }

    @Override
    public List<CandidateType> all() {
        return candidateTypeRepo.all();
    }

    @Override
    public CandidateType candidateTypeById(Long id) {
        return candidateTypeRepo.findCandidateTypeById(id);
    }

    @Override
    public List<CandidateType> allActiveAndExternal() {
        return candidateTypeRepo.allActiveAndExternal();
    }

    @Override
    public List<CandidateType> allActiveAndInternal() {
        return candidateTypeRepo.allActiveAndInternal();
    }

    @Override
    public CandidateTypeDto nameById(Long id) {
        return new CandidateTypeDto(candidateTypeById(id).getCandidateType());
    }
}
