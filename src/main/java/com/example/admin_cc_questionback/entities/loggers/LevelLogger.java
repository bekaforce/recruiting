package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "level_logger", schema = "loggers")
public class LevelLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "level_logger_seq")
    @SequenceGenerator(name = "level_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "level_logger_id_seq")
    private Long id;
    private String name;
    private String knowledge;
    private String status;
    private String login;
    private LocalDateTime dateTime;
}
