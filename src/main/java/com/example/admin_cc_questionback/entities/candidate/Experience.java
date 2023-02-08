package com.example.admin_cc_questionback.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "experience", schema = "vcv")
public class Experience {
    @Id
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