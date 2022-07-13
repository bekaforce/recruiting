package com.beeline.cc_question.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "knowledgeType", schema = "vcv")
public class KnowledgeType {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "knowledgeType_seq")
    @SequenceGenerator(name = "knowledgeType_seq", initialValue = 1, allocationSize = 1, sequenceName = "knowledgeType_id_seq")
    private Long id;
    private String name;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "candidateType_id", referencedColumnName = "id")
    private CandidateType candidateType;
    @OneToMany(mappedBy = "knowledgeType", cascade = CascadeType.REMOVE)
    private List<Knowledge> knowledgeList;
}
