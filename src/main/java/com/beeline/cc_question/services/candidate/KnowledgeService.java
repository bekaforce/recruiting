package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Knowledge;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> allByKnowledgeType(Long knowledgeType);
}
