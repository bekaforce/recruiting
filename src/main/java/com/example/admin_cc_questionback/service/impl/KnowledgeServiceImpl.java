package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.Knowledge;
import com.example.admin_cc_questionback.entities.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeDto;
import com.example.admin_cc_questionback.repository.KnowledgeRepo;
import com.example.admin_cc_questionback.service.KnowledgeService;
import com.example.admin_cc_questionback.service.impl.loggers.KnowledgeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
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
            knowledgeRepo.deleteById(id);
            knowledgeLoggerService.save(knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName(), LoggerStatus.DELETED);
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
            knowledgeLoggerService.save(knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName(), LoggerStatus.CREATED);
            return knowledge;
        }
        return null;
    }

    @Override
    public Knowledge update(Long id, String name) {
        Knowledge knowledge = knowledgeById(id);
        if (knowledge != null){
            String before = knowledge.getKnowledgeName();
            knowledge.setKnowledgeName(name);
            knowledgeRepo.save(knowledge);
            knowledgeLoggerService.saveUpdate(before, knowledge.getKnowledgeName(), knowledge.getKnowledgeType().getName());
            return knowledge;
        }
        return null;
    }

}
