package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "question", schema = "vcv")
public class Question {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", initialValue = 1, allocationSize = 1, sequenceName = "question_id_seq")
    private Long id;
    private String questionText;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;
    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;
    private Long position;

    public Question(long id, String questionText, QuestionType questionType, Long position) {
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
        this.position = position;
    }

    public Question(String questionText, QuestionType questionType, Long position) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.position = position;
    }

    public Question() {

    }
}
