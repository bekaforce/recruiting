package com.example.admin_cc_questionback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "knowledge", schema = "vcv")
public class Knowledge {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "knowledge_seq")
    @SequenceGenerator(name = "knowledge_seq", initialValue = 1, allocationSize = 1, sequenceName = "knowledge_id_seq")
    private Long id;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "knowledgeType_id", referencedColumnName = "id")
    private KnowledgeType knowledgeType;
    private String knowledgeName;
    @OneToMany(mappedBy = "knowledge", cascade = CascadeType.REMOVE)
    private List<Level> levels;

    public Knowledge(String knowledgeName, KnowledgeType knowledgeType) {
        this.knowledgeName = knowledgeName;
        this.knowledgeType = knowledgeType;
    }

    public Knowledge() {
    }
}
