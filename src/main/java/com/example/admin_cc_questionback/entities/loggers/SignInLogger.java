package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sing_in_logger", schema = "loggers")
public class SignInLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sing_in_logger_seq")
    @SequenceGenerator(name = "sing_in_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "sing_in_logger_id_seq")
    private Long id;
    private String login;
    private LocalDateTime dateTime;
    private String action;
}
