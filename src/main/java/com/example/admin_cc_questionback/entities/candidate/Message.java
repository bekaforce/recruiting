package com.example.admin_cc_questionback.entities.candidate;

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

    public Message(String text) {
        this.text = text;
    }

    public Message() {
    }
}