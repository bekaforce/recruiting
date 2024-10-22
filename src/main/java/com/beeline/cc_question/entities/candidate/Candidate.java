package com.beeline.cc_question.entities.candidate;

import com.beeline.cc_question.entities.interview.Essay;
import com.beeline.cc_question.entities.interview.Test;
import com.beeline.cc_question.entities.interview.VideoResult;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String birthday;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_id", referencedColumnName = "id")
    private Experience experience;
    private String education;
    private String schedule;
    private LocalDateTime registrationDate;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<VideoResult> videoResults;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<Essay> essays;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<Test> tests;
    @ManyToOne()
    @JoinColumn(name = "candidateType_id", referencedColumnName = "id")
    private CandidateType candidateType;
    private String comment;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private List<Questionnaire> questionnaire;
    private LocalDate invitationDate;
    private String stage;
    private String gender;
    private boolean isArchive;

    private String invitationLocation;
}
