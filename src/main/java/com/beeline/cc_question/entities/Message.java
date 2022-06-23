package com.beeline.cc_question.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message", schema = "vcv")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
}
