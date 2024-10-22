package com.beeline.cc_question.entities.candidate;

import com.beeline.cc_question.entities.interview.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "candidateType", schema = "vcv")
public class CandidateType {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "candidateType_seq")
    @SequenceGenerator(name = "candidateType_seq", initialValue = 1, allocationSize = 1, sequenceName = "candidateType_id_seq")
    private Long id;
    private String candidateType;
    private boolean internal;
    private String city;
    @OneToMany(mappedBy = "candidateType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;
    @OneToMany(mappedBy = "candidateType")
    @JsonIgnore
    private List<KnowledgeType> knowledgeTypes;
    @OneToMany(mappedBy = "candidateType")
    @JsonIgnore
    private List<Candidate> candidates;
    private boolean active;
    @ManyToOne()
    @JoinColumn(name = "teamType_id", referencedColumnName = "id")
    private TeamType teamType;
    private boolean schedule;
}
