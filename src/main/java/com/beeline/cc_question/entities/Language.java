package com.beeline.cc_question.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "language", schema = "vcv")
public class Language {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "language_seq")
    @SequenceGenerator(name = "language_seq", initialValue = 1, allocationSize = 1, sequenceName = "language_id_seq")
    private Long id;
    private boolean active;
    private String language;
    private String level;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;
}
