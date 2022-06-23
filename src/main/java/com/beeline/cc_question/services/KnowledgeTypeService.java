package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.KnowledgeType;

import java.util.List;

public interface KnowledgeTypeService {
    List<KnowledgeType> allByCandidateType(Long candidateType);
    KnowledgeType knowledgeTypeById(Long id);
}
