package com.example.admin_cc_questionback.entities.candidate;

import com.example.admin_cc_questionback.entities.candidate.Knowledge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "level", schema = "vcv")
public class Level {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "level_seq")
    @SequenceGenerator(name = "level_seq", initialValue = 1, allocationSize = 1, sequenceName = "level_id_seq")
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "knowledge_id", referencedColumnName = "id")
    private Knowledge knowledge;

    public Level(String name, Knowledge knowledge) {
        this.name = name;
        this.knowledge = knowledge;
    }

    public Level() {
    }
}
