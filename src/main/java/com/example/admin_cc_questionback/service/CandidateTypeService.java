package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.CandidateType;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;

import java.util.List;

public interface CandidateTypeService {
    CandidateType save(CandidateTypeDto candidateTypeDto);
    List<CandidateType> all();
    boolean delete(Long id);
    CandidateType update(CandidateTypeDto candidateTypeDto, Long id);
    CandidateType candidateTypeById(Long id);
}
