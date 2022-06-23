package com.example.admin_cc_questionback.service.impl;

import com.example.admin_cc_questionback.entities.CandidateType;
import com.example.admin_cc_questionback.entities.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeTypeDto;
import com.example.admin_cc_questionback.repository.KnowledgeTypeRepo;
import com.example.admin_cc_questionback.service.KnowledgeTypeService;
import com.example.admin_cc_questionback.service.impl.loggers.KnowledgeTypeLoggerServiceImpl;
import com.example.admin_cc_questionback.service.impl.loggers.LoggerStatus;
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
            knowledgeTypeLoggerService.save(knowledgeType.getName(), knowledgeType.getCandidateType().getCandidateType(), LoggerStatus.CREATED);
            return knowledgeType;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        KnowledgeType knowledgeType = knowledgeTypeById(id);
        if (knowledgeType != null){
            knowledgeTypeRepo.deleteById(id);
            knowledgeTypeLoggerService.save(knowledgeType.getName(), knowledgeType.getCandidateType().getCandidateType(), LoggerStatus.DELETED);
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
            knowledgeTypeLoggerService.saveUpdate(before, name, knowledgeType.getCandidateType().getCandidateType());
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
}
