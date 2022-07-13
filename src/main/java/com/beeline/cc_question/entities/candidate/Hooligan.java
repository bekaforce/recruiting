package com.beeline.cc_question.entities.candidate;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "hooligan", schema = "vcv")
public class Hooligan {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "hooligan_seq")
    @SequenceGenerator(name = "hooligan_seq", initialValue = 1, allocationSize = 1, sequenceName = "hooligan_id_seq")
    private Long id;
    private String name;
    private LocalDate birthday;
    private String reason;
}
