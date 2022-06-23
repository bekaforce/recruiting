package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.CandidateType;
import com.beeline.cc_question.repos.CandidateTypeRepo;
import com.beeline.cc_question.services.CandidateTypeService;
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
}
