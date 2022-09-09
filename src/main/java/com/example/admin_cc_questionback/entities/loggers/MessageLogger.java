package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "message_logger", schema = "loggers")
public class MessageLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "message_logger_seq")
    @SequenceGenerator(name = "message_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "message_logger_id_seq")
    private Long id;
    private String name;
    private String text;
    private String status;
    private String login;
    private LocalDateTime dateTime;
}
