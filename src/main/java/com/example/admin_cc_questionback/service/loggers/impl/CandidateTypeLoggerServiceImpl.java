package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.candidate.Department;
import com.example.admin_cc_questionback.entities.dtos.CandidateTypeUpdateDto;
import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.entities.loggers.CandidateTypeLogger;
import com.example.admin_cc_questionback.repository.loggers.CandidateTypeLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.CandidateTypeLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        candidateTypeLogger.setDateTime(loggerService.bishkekNow());
        candidateTypeLogger.setStatus(status);
        candidateTypeLogger.setLogin(loggerService.login());
        return candidateTypeLoggerRepo.save(candidateTypeLogger);
    }

    @Override
    public CandidateTypeLogger update(CandidateTypeUpdateDto candidateTypeDto, CandidateType candidateType, Department department) {
        CandidateTypeLogger candidateTypeLogger = new CandidateTypeLogger();
        candidateTypeLogger.setCandidateType(loggerService.setParam(candidateType.getCandidateType(), candidateTypeDto.getCandidateType()));
        candidateTypeLogger.setInternal(loggerService.setParam(String.valueOf(candidateType.isInternal()), String.valueOf(candidateTypeDto.isInternal())));
        candidateTypeLogger.setActive(loggerService.setParam(String.valueOf(candidateType.isActive()), String.valueOf(candidateTypeDto.isActive())));
        candidateTypeLogger.setDepartment(loggerService.setParam(department.getName(), department.getName()));
        candidateTypeLogger.setDateTime(loggerService.bishkekNow());
        candidateTypeLogger.setStatus(LoggerStatus.UPDATED);
        candidateTypeLogger.setLogin(loggerService.login());
        return candidateTypeLoggerRepo.save(candidateTypeLogger);
    }

    @Override
    public List<CandidateTypeLogger> all() {
        return candidateTypeLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(CandidateTypeLogger::getId).reversed()).collect(Collectors.toList());
    }
}
