package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.Knowledge;
import com.example.admin_cc_questionback.entities.candidate.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeDto;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeUpdateDto;
import com.example.admin_cc_questionback.repository.candidate.KnowledgeRepo;
import com.example.admin_cc_questionback.service.candidate.KnowledgeService;
import com.example.admin_cc_questionback.service.loggers.impl.KnowledgeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    private final KnowledgeRepo knowledgeRepo;
    private final KnowledgeTypeServiceImpl knowledgeTypeService;
    private final KnowledgeLoggerServiceImpl knowledgeLoggerService;

    public KnowledgeServiceImpl(KnowledgeRepo knowledgeRepo, KnowledgeTypeServiceImpl knowledgeTypeService, KnowledgeLoggerServiceImpl knowledgeLoggerService) {
        this.knowledgeRepo = knowledgeRepo;
        this.knowledgeTypeService = knowledgeTypeService;
        this.knowledgeLoggerService = knowledgeLoggerService;
    }



    @Override
    public List<Knowledge> allByKnowledgeType(Long knowledgeType) {
        return knowledgeRepo.findAllByKnowledgeType_Id(knowledgeType);
    }

    @Override
    public boolean delete(Long id) {
        if (knowledgeById(id) != null){
            Knowledge knowledge = knowledgeById(id);
            saveDeletedKnowledgeToLogs(knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName());
            knowledgeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Knowledge knowledgeById(Long id) {
        return knowledgeRepo.findKnowledgeById(id);
    }

    @Override
    public Knowledge save(KnowledgeDto knowledgeDto) {
        KnowledgeType knowledgeType = knowledgeTypeService.knowledgeTypeById(knowledgeDto.getKnowledgeType_id());
        if (knowledgeType != null){
            Knowledge knowledge = new Knowledge(knowledgeDto.getKnowledgeName(), knowledgeType);
            knowledgeRepo.save(knowledge);
            saveCreatedKnowledgeToLogs(knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName());
            return knowledge;
        }
        return null;
    }

    @Override
    public Knowledge update(KnowledgeUpdateDto knowledgeUpdateDto) {
        Knowledge knowledge = knowledgeById(knowledgeUpdateDto.getId());
        if (knowledge != null){
            String before = knowledge.getKnowledgeName();
            knowledge.setKnowledgeName(knowledgeUpdateDto.getKnowledgeName());
            knowledgeRepo.save(knowledge);
            saveUpdatedKnowledgeToLogs(before, knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName());
            return knowledge;
        }
        return null;
    }

    @Override
    public void saveCreatedKnowledgeToLogs(String knowledge, String knowledgeType) {
        knowledgeLoggerService.save(knowledge, knowledgeType, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedKnowledgeToLogs(String knowledge, String knowledgeType) {
        knowledgeLoggerService.save(knowledge, knowledgeType, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedKnowledgeToLogs(String before, String knowledge, String knowledgeType) {
        knowledgeLoggerService.saveUpdate(before, knowledge, knowledgeType);
    }

}
