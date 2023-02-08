package com.example.admin_cc_questionback.entities.interview;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "essay", schema = "vcv")
public class Essay {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "essay_seq")
    @SequenceGenerator(name = "essay_seq", initialValue = 1, allocationSize = 1, sequenceName = "essay_id_seq")
    private Long id;
    private String question;
    private String essay;
    private LocalDateTime sent;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    private String comment;
    private Long position;
}
