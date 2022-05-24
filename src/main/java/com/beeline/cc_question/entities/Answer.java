package com.beeline.cc_question.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answer", schema = "vcv")
public class Answer {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "answer_seq")
    @SequenceGenerator(name = "answer_seq", initialValue = 1, allocationSize = 1, sequenceName = "answer_id_seq")
    private Long id;
    private String content;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    public Answer(String content, Question question) {
        this.content = content;
        this.question = question;
    }

    public Answer() {

    }
}
