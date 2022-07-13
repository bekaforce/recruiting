package com.beeline.cc_question.entities.candidate.recaptcha;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "recaptcha", schema = "vcv")
public class Recaptcha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String name;
    private String key;

    public Recaptcha(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public Recaptcha() {

    }
}
