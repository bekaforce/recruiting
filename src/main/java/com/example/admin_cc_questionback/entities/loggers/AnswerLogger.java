package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "answer_logger", schema = "loggers")
public class AnswerLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "answer_logger_seq")
    @SequenceGenerator(name = "answer_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "answer_logger_id_seq")
    private Long id;
    private String content;
    private String question;
    private String correct;
    private String login;
    private LocalDateTime dateTime;
    private String status;
}
