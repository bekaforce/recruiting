package com.example.admin_cc_questionback.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "questionnaire", schema = "vcv")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "questionnaire_seq")
    @SequenceGenerator(name = "questionnaire_seq", initialValue = 1, allocationSize = 1, sequenceName = "questionnaire_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    private List<QuestionnaireAnswer> questionnaireAnswers;
}