package com.beeline.cc_question.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table
public class VideoResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_info_id")
    private ResultInfo resultInfo;

    public VideoResult() {
    }

    public VideoResult(String location, ResultInfo resultInfo) {
        this.location = location;
        this.resultInfo = resultInfo;
    }
}
