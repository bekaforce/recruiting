package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.KnowledgeType;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeTypeDto;

import java.util.List;

public interface KnowledgeTypeService {
    KnowledgeType save(KnowledgeTypeDto knowledgeTypeDto);
    boolean delete(Long id);
    KnowledgeType update(Long id, String name);
    KnowledgeType knowledgeTypeById(Long id);
    List<KnowledgeType> allByCandidateType(Long candidateType);
}
