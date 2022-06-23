package com.example.admin_cc_questionback.repository.loggers;

import com.example.admin_cc_questionback.entities.loggers.KnowledgeLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeLoggerRepo extends JpaRepository<KnowledgeLogger, Long> {

}
