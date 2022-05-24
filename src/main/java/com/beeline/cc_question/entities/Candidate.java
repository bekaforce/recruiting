package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "candidate", schema = "vcv")
public class Candidate {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "candidate_seq")
    @SequenceGenerator(name = "candidate_seq", initialValue = 1, allocationSize = 1, sequenceName = "candidate_id_seq")
    private Long id;
    private String vacancy;
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
    private List<VideoResult> videoResultList;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<Test> tests;

    public Candidate(String vacancy, String name, String phoneNumber, String email, String citizenship, String birthday, String address, String maritalStatus, Education education, List<Program> programs, String schedule, List<Language> languages, LocalDateTime now) {
        this.vacancy = vacancy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenship = citizenship;
        this.birthday = birthday;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.programs = programs;
        this.schedule = schedule;
        this.languages = languages;
        this.registrationDate = now;
    }

    public Candidate() {

    }
}
