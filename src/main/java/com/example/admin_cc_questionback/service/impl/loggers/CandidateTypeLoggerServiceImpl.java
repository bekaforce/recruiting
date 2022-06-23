package com.example.admin_cc_questionback.service.impl.loggers;

import com.example.admin_cc_questionback.entities.CandidateType;
import com.example.admin_cc_questionback.entities.Department;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeDto;
import com.example.admin_cc_questionback.entities.loggers.CandidateTypeLogger;
import com.example.admin_cc_questionback.repository.loggers.CandidateTypeLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.CandidateTypeLoggerService;
import org.springframework.stereotype.Service;

@Service
public class CandidateTypeLoggerServiceImpl implements CandidateTypeLoggerService {
    private final CandidateTypeLoggerRepo candidateTypeLoggerRepo;
    private final LoggerService loggerService;

    public CandidateTypeLoggerServiceImpl(CandidateTypeLoggerRepo candidateTypeLoggerRepo, LoggerService loggerService) {
        this.candidateTypeLoggerRepo = candidateTypeLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public CandidateTypeLogger save(String name, boolean internal, boolean active, String departmentName, String status) {
        CandidateTypeLogger candidateTypeLogger = new CandidateTypeLogger();
        candidateTypeLogger.setCandidateType(name);
        candidateTypeLogger.setInternal(String.valueOf(internal));
        candidateTypeLogger.setActive(String.valueOf(active));
        candidateTypeLogger.setDepartment(departmentName);
        candidateTypeLogger.setDatetime(loggerService.bishkekNow());
        candidateTypeLogger.setStatus(status);
        candidateTypeLogger.setLogin(loggerService.login());
        return candidateTypeLoggerRepo.save(candidateTypeLogger);
    }

    @Override
    public CandidateTypeLogger update(CandidateTypeDto candidateTypeDto, CandidateType candidateType, Department before, Department after) {
        CandidateTypeLogger candidateTypeLogger = new CandidateTypeLogger();
        candidateTypeLogger.setCandidateType(loggerService.setParam(candidateType.getCandidateType(), candidateTypeDto.getCandidateType()));
        candidateTypeLogger.setInternal(loggerService.setParam(String.valueOf(candidateType.isInternal()), String.valueOf(candidateTypeDto.isInternal())));
        candidateTypeLogger.setActive(loggerService.setParam(String.valueOf(candidateType.isActive()), String.valueOf(candidateTypeDto.isActive())));
        candidateTypeLogger.setDepartment(loggerService.setParam(before.getName(), after.getName()));
        candidateTypeLogger.setDatetime(loggerService.bishkekNow());
        candidateTypeLogger.setStatus(LoggerStatus.UPDATED);
        candidateTypeLogger.setLogin(loggerService.login());
        return candidateTypeLoggerRepo.save(candidateTypeLogger);
    }
}
