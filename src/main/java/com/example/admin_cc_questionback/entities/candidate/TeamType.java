package com.example.admin_cc_questionback.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "teamType", schema = "vcv")
public class TeamType {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "team_type_seq")
    @SequenceGenerator(name = "team_type_seq", initialValue = 1, allocationSize = 1, sequenceName = "team_type_id_seq")
    private Long id;
    private String name;
    private boolean toEducate;
    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @OneToMany(mappedBy = "teamType", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CandidateType> candidateTypes;


    public TeamType(String name, boolean toEducate) {
        this.name = name;
        this.toEducate = toEducate;
    }

    public TeamType() {

    }
}
