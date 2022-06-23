package com.example.admin_cc_questionback.service;

import com.example.admin_cc_questionback.entities.Knowledge;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeDto;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> allByKnowledgeType(Long knowledgeType);
    boolean delete(Long id);
    Knowledge knowledgeById(Long id);
    Knowledge save(KnowledgeDto knowledgeDto);
    Knowledge update(Long id, String name);
}
