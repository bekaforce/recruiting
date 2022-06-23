package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "knowledge_logger", schema = "loggers")
public class KnowledgeLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "knowledge_logger_seq")
    @SequenceGenerator(name = "knowledge_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "knowledge_logger_id_seq")
    private Long id;
    private String name;
    private String knowledgeType;
    private String status;
    private String login;
    private LocalDateTime dateTime;
}
