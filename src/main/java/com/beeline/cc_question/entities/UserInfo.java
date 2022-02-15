package com.beeline.cc_question.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String etc;

    @OneToMany(mappedBy = "userInfo", cascade = {CascadeType.REMOVE, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Result> results;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String video;

    public UserInfo() {
    }

    public UserInfo(String userId, String firstName, String lastName, String phone,
                      String address, String etc, List<Result> results) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.etc = etc;
        this.results = results;
    }

    public void addResultToUserInfo(Result result) {
        results.add(result);
    }
}
