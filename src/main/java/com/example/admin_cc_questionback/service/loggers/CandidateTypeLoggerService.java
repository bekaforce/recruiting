package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.candidate.TeamType;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.CandidateTypeLogger;

import java.util.List;

public interface CandidateTypeLoggerService {
    CandidateTypeLogger save(String name, boolean internal, boolean active, String teamTypeName, String status);
    CandidateTypeLogger update(CandidateTypeUpdateDto candidateTypeDto, CandidateType candidateType, TeamType teamType);
    List<CandidateTypeLogger> all();
}
