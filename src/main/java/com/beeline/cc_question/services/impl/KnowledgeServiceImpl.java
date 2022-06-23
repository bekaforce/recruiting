package com.beeline.cc_question.services.impl;

import com.beeline.cc_question.entities.Knowledge;
import com.beeline.cc_question.repos.KnowledgeRepo;
import com.beeline.cc_question.services.KnowledgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    private final KnowledgeRepo knowledgeRepo;

    public KnowledgeServiceImpl(KnowledgeRepo knowledgeRepo) {
        this.knowledgeRepo = knowledgeRepo;
    }

    @Override
    public List<Knowledge> allByKnowledgeType(Long knowledgeType) {
        return knowledgeRepo.findAllByKnowledgeType_Id(knowledgeType);
    }
}
