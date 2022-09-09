package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.entities.loggers.KnowledgeTypeLogger;
import com.example.admin_cc_questionback.repository.loggers.KnowledgeTypeLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.KnowledgeTypeLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeTypeLoggerServiceImpl implements KnowledgeTypeLoggerService {
    private final KnowledgeTypeLoggerRepo knowledgeTypeLoggerRepo;
    private final LoggerService loggerService;

    public KnowledgeTypeLoggerServiceImpl(KnowledgeTypeLoggerRepo knowledgeTypeLoggerRepo, LoggerService loggerService) {
        this.knowledgeTypeLoggerRepo = knowledgeTypeLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public KnowledgeTypeLogger save(String name, String candidateType, String status) {
        KnowledgeTypeLogger knowledgeTypeLogger = new KnowledgeTypeLogger();
        knowledgeTypeLogger.setName(name);
        knowledgeTypeLogger.setCandidateType(candidateType);
        knowledgeTypeLogger.setStatus(status);
        knowledgeTypeLogger.setLogin(loggerService.login());
        knowledgeTypeLogger.setDateTime(loggerService.bishkekNow());
        return knowledgeTypeLoggerRepo.save(knowledgeTypeLogger);
    }

    @Override
    public KnowledgeTypeLogger saveUpdate(String nameBefore, String nameAfter, String candidateType) {
        KnowledgeTypeLogger knowledgeTypeLogger = new KnowledgeTypeLogger();
        if (!loggerService.beforeAndAfter(nameBefore, nameAfter)){
            knowledgeTypeLogger.setName(loggerService.setParam(nameBefore, nameAfter));
            knowledgeTypeLogger.setCandidateType(candidateType);
            knowledgeTypeLogger.setStatus(LoggerStatus.UPDATED);
            knowledgeTypeLogger.setLogin(loggerService.login());
            knowledgeTypeLogger.setDateTime(loggerService.bishkekNow());
            return knowledgeTypeLoggerRepo.save(knowledgeTypeLogger);
        }
        return null;
    }

    @Override
    public List<KnowledgeTypeLogger> all() {
        return knowledgeTypeLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(KnowledgeTypeLogger::getId).reversed()).collect(Collectors.toList());
    }
}
