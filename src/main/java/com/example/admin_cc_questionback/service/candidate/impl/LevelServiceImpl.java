package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Knowledge;
import com.example.admin_cc_questionback.entities.candidate.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;
import com.example.admin_cc_questionback.entities.dtos.LevelUpdateDto;
import com.example.admin_cc_questionback.repository.candidate.LevelRepo;
import com.example.admin_cc_questionback.service.candidate.LevelService;
import com.example.admin_cc_questionback.service.loggers.impl.LevelLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepo levelRepo;
    private final KnowledgeServiceImpl knowledgeService;
    private final LevelLoggerServiceImpl loggerService;

    public LevelServiceImpl(LevelRepo levelRepo, KnowledgeServiceImpl knowledgeService, LevelLoggerServiceImpl loggerService) {
        this.levelRepo = levelRepo;
        this.knowledgeService = knowledgeService;
        this.loggerService = loggerService;
    }


    @Override
    public Level save(LevelDto levelDto) {
        Knowledge knowledge = knowledgeService.knowledgeById(levelDto.getKnowledge_id());
        if (knowledge != null){
            Level level = new Level(levelDto.getName(), knowledge);
            levelRepo.save(level);
            saveCreatedLevelToLogs(level.getName(), level.getKnowledge().getKnowledgeName());
            return level;
        }
        return null;
    }

    @Override
    public Level update(LevelUpdateDto levelUpdateDto) {
        Level level = levelRepo.findLevelById(levelUpdateDto.getId());
        if (level != null){
            String before = level.getName();
            level.setName(levelUpdateDto.getName());
            levelRepo.save(level);
            saveUpdatedLevelToLogs(before, level.getName(), level.getKnowledge().getKnowledgeName());
            return level;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Level level = levelRepo.findLevelById(id);
        if (level != null){
            levelRepo.deleteById(id);
            saveDeletedLevelToLogs(level.getName(), level.getKnowledge().getKnowledgeName());
            return true;
        }
        return false;
    }

    @Override
    public void saveCreatedLevelToLogs(String level, String knowledge) {
        loggerService.save(level, knowledge, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedLevelToLogs(String level, String knowledge) {
        loggerService.save(level, knowledge, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedLevelToLogs(String before, String level, String knowledge) {
        loggerService.saveUpdate(before, level, knowledge);
    }
}
