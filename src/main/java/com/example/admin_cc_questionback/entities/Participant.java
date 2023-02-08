package com.example.admin_cc_questionback.entities;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "participant", schema = "vcv")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Boolean gender;
    private String channel;
    private LocalDate birthday;
    private String email;
    private String address;
    private String candidate_type;
    private String phone_number;
    private String status;
    private String city;
    private LocalDate invitation_date;
}
