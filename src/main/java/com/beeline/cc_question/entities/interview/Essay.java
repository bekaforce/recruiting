package com.beeline.cc_question.entities.interview;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "essay", schema = "vcv")
public class Essay {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "essay_seq")
    @SequenceGenerator(name = "essay_seq", initialValue = 1, allocationSize = 1, sequenceName = "essay_id_seq")
    @JsonIgnore
    private Long id;
    private String question;
    @Column(columnDefinition = "TEXT")
    private String essay;
    private LocalDateTime sent;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    private String comment;
    private Long position;

    public Essay(String question, String essay, LocalDateTime sent, Candidate candidate, Long position) {
        this.question = question;
        this.essay = essay;
        this.sent = sent;
        this.candidate = candidate;
        this.position = position;
    }

    public Essay() {

    }
}
