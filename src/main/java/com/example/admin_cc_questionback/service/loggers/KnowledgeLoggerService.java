package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.KnowledgeLogger;

import java.util.List;

public interface KnowledgeLoggerService {
    KnowledgeLogger save(String name, String knowledgeType, String status);
    KnowledgeLogger saveUpdate(String nameBefore, String nameAfter, String knowledgeType);
    List<KnowledgeLogger> all();
}
