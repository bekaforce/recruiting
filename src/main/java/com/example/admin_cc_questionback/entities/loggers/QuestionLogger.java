package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "question_logger", schema = "loggers")
public class QuestionLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "question_logger_seq")
    @SequenceGenerator(name = "question_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "question_logger_id_seq")
    private Long id;
    private String questionText;
    private String questionType;
    private String milliseconds;
    private String candidateType;
    private String key;
    private String login;
    private String position;
    private LocalDateTime dateTime;
    private String status;
}
