package com.example.admin_cc_questionback.service.candidate.impl;

import com.example.admin_cc_questionback.entities.candidate.CandidateType;
import com.example.admin_cc_questionback.entities.candidate.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeTypeDto;
import com.example.admin_cc_questionback.repository.candidate.KnowledgeTypeRepo;
import com.example.admin_cc_questionback.service.candidate.KnowledgeTypeService;
import com.example.admin_cc_questionback.service.loggers.impl.KnowledgeTypeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.loggers.impl.LoggerStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeTypeServiceImpl implements KnowledgeTypeService {
    private final KnowledgeTypeRepo knowledgeTypeRepo;
    private final CandidateTypeServiceImpl candidateTypeService;
    private final KnowledgeTypeLoggerServiceImpl knowledgeTypeLoggerService;

    public KnowledgeTypeServiceImpl(KnowledgeTypeRepo knowledgeTypeRepo, CandidateTypeServiceImpl candidateTypeService, KnowledgeTypeLoggerServiceImpl knowledgeTypeLoggerService) {
        this.knowledgeTypeRepo = knowledgeTypeRepo;
        this.candidateTypeService = candidateTypeService;
        this.knowledgeTypeLoggerService = knowledgeTypeLoggerService;
    }

    @Override
    public KnowledgeType save(KnowledgeTypeDto knowledgeTypeDto) {
        CandidateType candidateType = candidateTypeService.candidateTypeById(knowledgeTypeDto.getCandidateTypeId());
        if (candidateType != null){
            KnowledgeType knowledgeType = new KnowledgeType(knowledgeTypeDto.getName(), candidateType);
            knowledgeTypeRepo.save(knowledgeType);
            saveCreatedKnowledgeTypeToLogs(knowledgeType.getName(), knowledgeType.getCandidateType().getCandidateType());
            return knowledgeType;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        KnowledgeType knowledgeType = knowledgeTypeById(id);
        if (knowledgeType != null){
            saveDeletedKnowledgeTypeToLogs(knowledgeType.getName(), knowledgeType.getCandidateType().getCandidateType());
            knowledgeTypeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public KnowledgeType update(Long id, String name) {
        KnowledgeType knowledgeType = knowledgeTypeById(id);
        if (knowledgeType != null && !knowledgeType.getName().equals(name)){
            String before = knowledgeType.getName();
            knowledgeType.setName(name);
            knowledgeTypeRepo.save(knowledgeType);
            saveUpdatedKnowledgeTypeToLogs(before, knowledgeType.getName(), knowledgeType.getCandidateType().getCandidateType());
            return knowledgeType;
        }
        return null;
    }

    @Override
    public KnowledgeType knowledgeTypeById(Long id) {
        return knowledgeTypeRepo.findKnowledgeTypeById(id);
    }

    @Override
    public List<KnowledgeType> allByCandidateType(Long candidateType) {
        return knowledgeTypeRepo.findAllByCandidateType_Id(candidateType);
    }

    @Override
    public void saveCreatedKnowledgeTypeToLogs(String knowledgeType, String candidateType) {
        knowledgeTypeLoggerService.save(knowledgeType, candidateType, LoggerStatus.CREATED);
    }

    @Override
    public void saveDeletedKnowledgeTypeToLogs(String knowledgeType, String candidateType) {
        knowledgeTypeLoggerService.save(knowledgeType, candidateType, LoggerStatus.DELETED);
    }

    @Override
    public void saveUpdatedKnowledgeTypeToLogs(String before, String knowledge, String candidateType) {
        knowledgeTypeLoggerService.saveUpdate(before, knowledge, candidateType);
    }
}
