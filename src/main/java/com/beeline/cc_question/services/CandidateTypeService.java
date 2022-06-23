package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.CandidateType;

import java.util.List;

public interface CandidateTypeService {
    List<CandidateType> all();
    CandidateType candidateTypeById(Long id);
    List<CandidateType> allActiveAndExternal();
}
