package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "question_id")
    private Question question;

    @Transient
    private List<Answer> answers;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Result() {
    }

    public Result(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}
