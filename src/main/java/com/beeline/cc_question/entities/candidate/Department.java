package com.beeline.cc_question.entities.candidate;

import com.beeline.cc_question.entities.candidate.CandidateType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "department", schema = "vcv")
public class Department {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", initialValue = 1, allocationSize = 1, sequenceName = "department_id_seq")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "candidateType", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CandidateType> candidateTypes;

    public Department(String name) {
        this.name = name;
    }

    public Department() {

    }
}