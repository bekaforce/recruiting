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
    private UserInfo userInfo;

    public VideoResult() {
    }

    public VideoResult(String location, UserInfo userInfo) {
        this.location = location;
        this.userInfo = userInfo;
    }
}
