package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "knowledgeType_logger", schema = "loggers")
public class KnowledgeTypeLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "knowledgeType_logger_seq")
    @SequenceGenerator(name = "knowledgeType_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "knowledgeType_logger_id_seq")
    private Long id;
    private String name;
    private String candidateType;
    private String status;
    private String login;
    private LocalDateTime dateTime;
}
