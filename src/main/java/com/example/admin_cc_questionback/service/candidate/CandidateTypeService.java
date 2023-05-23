package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeUpdateDto;

import java.util.List;

public interface CandidateTypeService {
    CandidateType save(CandidateTypeDto candidateTypeDto);
    List<CandidateType> all();
    boolean delete(Long id);
    CandidateType update(CandidateTypeUpdateDto candidateTypeDto, Long id);
    CandidateType candidateTypeById(Long id);
}