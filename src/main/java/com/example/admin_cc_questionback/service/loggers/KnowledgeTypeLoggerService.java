package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.KnowledgeTypeLogger;

import java.util.List;


public interface KnowledgeTypeLoggerService {
    KnowledgeTypeLogger save(String name, String candidateType, String status);
    KnowledgeTypeLogger saveUpdate(String nameBefore, String nameAfter, String candidateType);
    List<KnowledgeTypeLogger> all();
}
