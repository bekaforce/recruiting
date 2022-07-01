package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.KnowledgeType;

import java.util.List;

public interface KnowledgeTypeService {
    List<KnowledgeType> allByCandidateType(Long candidateType);
    KnowledgeType knowledgeTypeById(Long id);
}
