package com.example.admin_cc_questionback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "programs", schema = "vcv")
public class Program {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "program_seq")
    @SequenceGenerator(name = "program_seq", initialValue = 1, allocationSize = 1, sequenceName = "program_id_seq")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;
}
