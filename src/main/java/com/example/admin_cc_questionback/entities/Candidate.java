package com.example.admin_cc_questionback.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "candidate", schema = "vcv")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String citizenship;
    private String birthday;
    private String address;
    private String maritalStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id", referencedColumnName = "id")
    private Education education;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<Program> programs;
    private String schedule;
    private LocalDateTime registrationDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<Language> languages;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<VideoResult> videoResults;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<Test> tests;


    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", education=" + education +
                ", programs=" + programs +
                ", schedule='" + schedule + '\'' +
                ", registrationDate=" + registrationDate +
                ", languages=" + languages +
                '}';
    }
}
