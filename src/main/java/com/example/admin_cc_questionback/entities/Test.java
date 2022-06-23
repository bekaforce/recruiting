package com.example.admin_cc_questionback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "test", schema = "vcv")
public class Test {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", initialValue = 1, allocationSize = 1, sequenceName = "test_id_seq")
    @JsonIgnore
    private Long id;
    private String question;
    private String answer;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
    private LocalDateTime datetime;
    @Column(nullable = true)
    private boolean correct;
    private boolean key;
}
