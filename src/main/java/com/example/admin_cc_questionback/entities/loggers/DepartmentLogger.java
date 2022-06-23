package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "department_logger", schema = "loggers")
public class DepartmentLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "department_logger_seq")
    @SequenceGenerator(name = "department_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "department_logger_id_seq")
    private Long id;
    private String login;
    private LocalDateTime localDateTime;
    private String before;
    private String after;
    private String status;
}
