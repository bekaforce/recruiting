package com.example.admin_cc_questionback.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department", schema = "vcv")
public class Department {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", initialValue = 1, allocationSize = 1, sequenceName = "department_id_seq")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CandidateType> candidateTypes;

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CandidateType> getCandidateTypes() {
        return candidateTypes;
    }

    public void setCandidateTypes(List<CandidateType> candidateTypes) {
        this.candidateTypes = candidateTypes;
    }

    public Department() {

    }
}
