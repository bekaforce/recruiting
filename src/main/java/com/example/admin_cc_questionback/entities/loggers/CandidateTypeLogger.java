package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "candidateType_logger", schema = "loggers")
public class CandidateTypeLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "candidateType_logger_seq")
    @SequenceGenerator(name = "candidateType_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "candidateType_logger_id_seq")
    private Long id;
    private String candidateType;
    private String internal;
    private String active;
    private String department;
    private LocalDateTime datetime;
    private String status;
    private String login;
}
