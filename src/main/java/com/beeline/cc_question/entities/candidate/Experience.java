package com.beeline.cc_question.entities.candidate;

import com.beeline.cc_question.entities.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "experience", schema = "vcv")
public class Experience {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "experience_seq")
    @SequenceGenerator(name = "experience_seq", initialValue = 1, allocationSize = 1, sequenceName = "experience_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String position;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "experience")
    private Candidate candidate;
}
