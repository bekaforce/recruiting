package com.beeline.cc_question.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedback", schema = "vcv")
public class Feedback {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "feedback_seq")
    @SequenceGenerator(name = "feedback_seq", initialValue = 1, allocationSize = 1, sequenceName = "feedback_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    private String comment;
    private LocalDateTime date;
}
