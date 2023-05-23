package com.example.admin_cc_questionback.entities.loggers;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "invitation_logger", schema = "loggers")
public class InvitationLogger {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "invitation_logger_seq")
    @SequenceGenerator(name = "invitation_logger_seq", initialValue = 1, allocationSize = 1, sequenceName = "invitation_logger_id_seq")
    private Long id;
    private String login;
    private String candidateType;
    private String candidate;
    private String status;
    private String location;
    private LocalDateTime DateTime;
}
