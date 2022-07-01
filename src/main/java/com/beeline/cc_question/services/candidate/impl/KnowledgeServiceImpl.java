package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Knowledge;
import com.beeline.cc_question.repos.candidate.KnowledgeRepo;
import com.beeline.cc_question.services.candidate.KnowledgeService;
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
