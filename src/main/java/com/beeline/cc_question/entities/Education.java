package com.beeline.cc_question.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "education", schema = "vcv")
public class Education {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "education_seq")
    @SequenceGenerator(name = "education_seq", initialValue = 1, allocationSize = 1, sequenceName = "education_id_seq")
    @JsonIgnore
    private Long id;
    private String type;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String speciality;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "education")
    private Candidate candidate;
}
