package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.entities.loggers.CandidateTypeLogger;

public interface CandidateTypeLoggerService {
    CandidateTypeLogger save(String name, boolean internal, boolean active, String departmentName, String status);
    CandidateTypeLogger update(CandidateTypeDto candidateTypeDto, CandidateType candidateType, Department before, Department after);
}
