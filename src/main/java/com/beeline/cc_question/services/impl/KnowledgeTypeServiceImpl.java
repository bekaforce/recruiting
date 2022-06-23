package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.KnowledgeType;
import com.beeline.cc_question.repos.KnowledgeTypeRepo;
import com.beeline.cc_question.services.KnowledgeTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeTypeServiceImpl implements KnowledgeTypeService {
    private final KnowledgeTypeRepo knowledgeTypeRepo;

    public KnowledgeTypeServiceImpl(KnowledgeTypeRepo knowledgeTypeRepo) {
        this.knowledgeTypeRepo = knowledgeTypeRepo;
    }

    @Override
    public List<KnowledgeType> allByCandidateType(Long candidateType) {
        return knowledgeTypeRepo.findAllByCandidateType_Id(candidateType);
    }

    @Override
    public KnowledgeType knowledgeTypeById(Long id) {
        return knowledgeTypeRepo.findKnowledgeTypeById(id);
    }
}
