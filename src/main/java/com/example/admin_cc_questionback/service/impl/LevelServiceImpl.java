package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Knowledge;
import com.example.admin_cc_questionback.entities.Level;
import com.example.admin_cc_questionback.entities.dtos.LevelDto;
import com.example.admin_cc_questionback.repository.LevelRepo;
import com.example.admin_cc_questionback.service.LevelService;
import com.example.admin_cc_questionback.service.impl.loggers.LevelLoggerServiceImpl;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
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
            loggerService.save(level.getName(), level.getKnowledge().getKnowledgeName(), LoggerStatus.CREATED);
            return level;
        }
        return null;
    }

    @Override
    public Level update(Long id, String name) {
        Level level = levelRepo.findLevelById(id);
        if (level != null){
            String before = level.getName();
            level.setName(name);
            levelRepo.save(level);
            loggerService.saveUpdate(before, name, level.getKnowledge().getKnowledgeName());
            return level;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Level level = levelRepo.findLevelById(id);
        if (level != null){
            levelRepo.deleteById(id);
            loggerService.save(level.getName(), level.getKnowledge().getKnowledgeName(), LoggerStatus.DELETED);
            return true;
        }
        return false;
    }
}
