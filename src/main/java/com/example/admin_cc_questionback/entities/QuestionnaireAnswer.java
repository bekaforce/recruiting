package com.example.admin_cc_questionback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questionnaireAnswer", schema = "vcv")
public class QuestionnaireAnswer {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "questionnaire_answer_seq")
    @SequenceGenerator(name = "questionnaire_answer_seq", initialValue = 1, allocationSize = 1, sequenceName = "questionnaire_answer_id_seq")
    @JsonIgnore
    private Long id;
    private String knowledge;
    private String level;
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    @JsonIgnore
    private Questionnaire questionnaire;
}
