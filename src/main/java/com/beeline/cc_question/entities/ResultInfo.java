package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class ResultInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String etc;

    @OneToMany(mappedBy = "resultInfo", cascade = {CascadeType.REMOVE, CascadeType.MERGE,
                                                    CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Result> results;

    public ResultInfo() {
    }

    public ResultInfo(String userId, String firstName, String lastName, String phone,
                      String address, String etc, List<Result> results) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.etc = etc;
        this.results = results;
    }
}
