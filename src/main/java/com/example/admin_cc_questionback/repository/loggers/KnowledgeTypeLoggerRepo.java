package com.example.admin_cc_questionback.repository.loggers;

import com.example.admin_cc_questionback.entities.loggers.KnowledgeTypeLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeTypeLoggerRepo extends JpaRepository<KnowledgeTypeLogger, Long> {

}
