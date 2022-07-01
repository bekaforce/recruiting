package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.CandidateType;

import java.util.List;

public interface CandidateTypeService {
    List<CandidateType> all();
    CandidateType candidateTypeById(Long id);
    List<CandidateType> allActiveAndExternal();
}
