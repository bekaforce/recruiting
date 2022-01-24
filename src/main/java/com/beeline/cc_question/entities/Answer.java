package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private boolean isTrue;

    public Answer() {
    }

    public Answer(int id, String content, boolean isTrue) {
        this.id = id;
        this.content = content;
        this.isTrue = isTrue;
    }
}
