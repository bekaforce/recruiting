package com.example.admin_cc_questionback.service.impl.loggers;

import com.example.admin_cc_questionback.entities.loggers.LevelLogger;
import com.example.admin_cc_questionback.repository.loggers.LevelLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.LevelLoggerService;
import org.springframework.stereotype.Service;

@Service
public class LevelLoggerServiceImpl implements LevelLoggerService {
    private final LevelLoggerRepo levelLoggerRepo;
    private final LoggerService loggerService;

    public LevelLoggerServiceImpl(LevelLoggerRepo levelLoggerRepo, LoggerService loggerService) {
        this.levelLoggerRepo = levelLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public LevelLogger save(String name, String knowledge, String status) {
        LevelLogger levelLogger = new LevelLogger();
        levelLogger.setName(name);
        levelLogger.setKnowledge(knowledge);
        levelLogger.setLogin(loggerService.login());
        levelLogger.setDateTime(loggerService.bishkekNow());
        levelLogger.setStatus(status);
        return levelLoggerRepo.save(levelLogger);
    }

    @Override
    public LevelLogger saveUpdate(String nameBefore, String nameAfter, String knowledge) {
        LevelLogger levelLogger = new LevelLogger();
        levelLogger.setName(loggerService.setParam(nameBefore, nameAfter));
        levelLogger.setKnowledge(knowledge);
        levelLogger.setLogin(loggerService.login());
        levelLogger.setDateTime(loggerService.bishkekNow());
        levelLogger.setStatus(LoggerStatus.UPDATED);
        return levelLoggerRepo.save(levelLogger);
    }
}
