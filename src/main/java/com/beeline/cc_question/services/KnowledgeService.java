package com.beeline.cc_question.services;

import com.beeline.cc_question.entities.Knowledge;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> allByKnowledgeType(Long knowledgeType);
}
