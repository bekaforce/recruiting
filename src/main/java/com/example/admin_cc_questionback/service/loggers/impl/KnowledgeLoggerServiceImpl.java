package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.loggers.AnswerLogger;
import com.example.admin_cc_questionback.entities.loggers.KnowledgeLogger;
import com.example.admin_cc_questionback.repository.loggers.KnowledgeLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.KnowledgeLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeLoggerServiceImpl implements KnowledgeLoggerService {
    private final KnowledgeLoggerRepo knowledgeLoggerRepo;
    private final LoggerService loggerService;

    public KnowledgeLoggerServiceImpl(KnowledgeLoggerRepo knowledgeLoggerRepo, LoggerService loggerService) {
        this.knowledgeLoggerRepo = knowledgeLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public KnowledgeLogger save(String name, String knowledgeType, String status) {
        KnowledgeLogger knowledgeLogger = new KnowledgeLogger();
        knowledgeLogger.setName(name);
        knowledgeLogger.setKnowledgeType(knowledgeType);
        knowledgeLogger.setStatus(status);
        knowledgeLogger.setLogin(loggerService.login());
        knowledgeLogger.setDateTime(loggerService.bishkekNow());
        return knowledgeLoggerRepo.save(knowledgeLogger);
    }

    @Override
    public KnowledgeLogger saveUpdate(String nameBefore, String nameAfter, String knowledgeType) {
        KnowledgeLogger knowledgeLogger = new KnowledgeLogger();
        knowledgeLogger.setName(loggerService.setParam(nameBefore, nameAfter));
        knowledgeLogger.setKnowledgeType(knowledgeType);
        knowledgeLogger.setStatus(LoggerStatus.UPDATED);
        knowledgeLogger.setLogin(loggerService.login());
        knowledgeLogger.setDateTime(loggerService.bishkekNow());
        return knowledgeLoggerRepo.save(knowledgeLogger);
    }

    @Override
    public List<KnowledgeLogger> all() {
        return knowledgeLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(KnowledgeLogger::getId).reversed()).collect(Collectors.toList());
    }


}