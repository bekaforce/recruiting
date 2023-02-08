package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "teamType_logger", schema = "loggers")
public class TeamTypeLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "team_type_logger_seq")
    @SequenceGenerator(name = "team_type_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "team_type_logger_id_seq")
    private Long id;
    private String name;
    private String toEducate;
    private LocalDateTime dateTime;
    private String login;
    private String status;
    private String department;

    public TeamTypeLogger(String name, String toEducate, LocalDateTime dateTime, String login, String status, String department) {
        this.name = name;
        this.toEducate = toEducate;
        this.dateTime = dateTime;
        this.login = login;
        this.status = status;
        this.department = department;
    }

    public TeamTypeLogger() {
    }
}
