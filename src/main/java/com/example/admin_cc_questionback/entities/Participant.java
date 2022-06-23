package com.example.admin_cc_questionback.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "participant", schema = "vcv")
public class Participant {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "participant_seq")
    @SequenceGenerator(name = "participant_seq", initialValue = 1, allocationSize = 1, sequenceName = "participant_id_seq")
    private Long id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String address;
    private String candidate_type;
    private String phone_number;
    private String status;

    public Participant(String name, LocalDate birthday, String email, String address, String candidate_type, String status, String phone_number) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.candidate_type = candidate_type;
        this.status = status;
        this.phone_number = phone_number;
    }


    public Participant() {

    }
}
