package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String type; //(choices - checkbox, radio)

    private boolean hasVideo;

    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="question_id")
    private List<Answer> answers;

    public Question(){
    }

    public Question(String title, String type, boolean hasVideo, int duration) {
        this.title = title;
        this.type = type;
        this.hasVideo = hasVideo;
        this.duration = duration;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    @Override
    public String toString() {
        String result = "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", hasVideo=" + hasVideo +
                '}';
        if (hasVideo == false)  {
            result += ", answers=" + answers;
        } else {
            result += ", duration=" + duration;
        }
        return result;
    }
}

