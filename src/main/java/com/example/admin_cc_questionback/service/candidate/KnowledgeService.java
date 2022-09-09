package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Knowledge;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeDto;
import com.example.admin_cc_questionback.entities.dtos.KnowledgeUpdateDto;

import java.util.List;

public interface KnowledgeService {
    List<Knowledge> allByKnowledgeType(Long knowledgeType);
    boolean delete(Long id);
    Knowledge knowledgeById(Long id);
    Knowledge save(KnowledgeDto knowledgeDto);
    Knowledge update(KnowledgeUpdateDto knowledgeUpdateDto);
    void saveCreatedKnowledgeToLogs(String knowledge, String knowledgeType);
    void saveDeletedKnowledgeToLogs(String knowledge, String knowledgeType);
    void saveUpdatedKnowledgeToLogs(String before, String knowledge, String knowledgeType);
}
